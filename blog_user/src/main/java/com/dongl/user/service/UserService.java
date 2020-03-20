package com.dongl.user.service;

import com.dongl.entity.Result;
import com.dongl.rediesutils.RedisUtil;
import com.dongl.user.config.BCryptUtil;
import com.dongl.user.dao.UserDao;
import com.dongl.user.entity.User;
import com.dongl.user.rabbitmq.SendMsgService;
import com.dongl.utils.IdWorker;
import com.dongl.utils.JwtUtil;
import com.dongl.utils.StrUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 服务层
 *
 * @author administrator
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BCryptUtil bCryptUtil;

    @Autowired
    private SendMsgService sendMsgService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param user
     */
    public void add(User user) {

        user.setId(idWorker.nextId() + "");
        /**  关注数 **/
        user.setFollowcount(0);
        /**  粉丝数 **/
        user.setFanscount(0);
        /**  在线时长数 **/
        user.setOnline(0L);

        /**  更新日期 **/
        user.setUpdatedate(new Date());
        /**  最后登录日期 **/
        user.setLastdate(new Date());
        userDao.save(user);
    }

    /**
     * 修改
     *
     * @param user
     */
    public void update(User user) {
        userDao.save(user);
    }

    /**
     * 删除用户时，需要登录切有相应的权限
     * @param id
     */
    public void deleteById(String id) {
        Object claims_admin = request.getAttribute("claims_admin");
        if (StrUtils.isEmptyObj(claims_admin)){
            throw new RuntimeException("权限不足");
        }
        userDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 手机号码
                if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
                    predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + (String) searchMap.get("mobile") + "%"));
                }
                // 密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 昵称
                if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
                    predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
                }
                // 性别
                if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
                    predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
                }
                // 头像
                if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
                    predicateList.add(cb.like(root.get("avatar").as(String.class), "%" + (String) searchMap.get("avatar") + "%"));
                }
                // E-Mail
                if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
                    predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
                }
                // 兴趣
                if (searchMap.get("interest") != null && !"".equals(searchMap.get("interest"))) {
                    predicateList.add(cb.like(root.get("interest").as(String.class), "%" + (String) searchMap.get("interest") + "%"));
                }
                // 个性
                if (searchMap.get("personality") != null && !"".equals(searchMap.get("personality"))) {
                    predicateList.add(cb.like(root.get("personality").as(String.class), "%" + (String) searchMap.get("personality") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public Result sendShortMessage(String mobile) {
        String key = "checkCode" + mobile;

        // 该验证应该查询用户表进行验证
        boolean exists = redisUtil.exists(key);
        if (exists){
            return Result.error("验证码已发送，如未收到10分钟后重试！");
        }
        // 1.获取随机验证码
        String chechCode = RandomStringUtils.randomNumeric(6);
        // 2. 向Redis 中存放验证码
        redisUtil.set(key, chechCode, 600);

        // 3. 向 RabbitMQ中发送 验证码 ，在 SmsListener.java 中调用 阿里云 短信发送功能消费 RabbitMQ 中的消息。
        Map<String, String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("checkCode",chechCode);
        sendMsgService.sendMessage(map);

        System.out.println("验证码："+ chechCode);
        return  Result.success("验证码发送成功！");
    }


    public Result regist(User user, String code) {
        String mobile = user.getMobile();
        String password = user.getPassword();
        String key = "checkCode"+mobile ;
        // 1. 判断 手机号是否正确
        boolean exists = redisUtil.exists(key);
        if (!exists){
            return Result.error("手机号码不正确！");
        }
        // 2. 判断手机号是否已被注册
        User userIsExists = userDao.findByMobile(mobile);
        if (null != userIsExists){
            return Result.error("该手机号已经被注册！");
        }
        // 3. 判断验证码是否存在或失效
        Object value = redisUtil.get(key);
        if (null == value){
            return Result.error("验证码失效！");
        }

        String keep_code = String.valueOf(value);
        // 4. 判断验证码是否相等
        if (!keep_code.equals(code)){
            return Result.error("验证码不正确!");
        }

        // 5. 密码加密
        String encoderPassword = bCryptUtil.encoder(password);
        user.setPassword(encoderPassword);
        // 6. 调用数据库保存 用户信息。
        /**  注册日期 **/
        user.setRegdate(new Date());
        this.add(user);
        return Result.success("注册成功！");
    }

    public Result login(Map map, String code) {
        // 1. 验证验证码是否相等

        // 2.
        String mobile = String.valueOf(map.get("mobile"));
        String password = String.valueOf(map.get("password"));
        User user = userDao.findByMobile(mobile);
        if (null == user){
            return Result.error("该用户暂未注册！");
        }
        String encoderPassword = user.getPassword();
        // 3.判断密码是否相等
        Boolean bool = bCryptUtil.matches(password,encoderPassword);
        if (!bool){
            return Result.error("账户或密码不正确！");
        }
        // 4. 登陆成功跳转到首页，待实现

        return Result.success("登陆成功！");
    }
}
