package com.dongl.user.service;

import com.dongl.entity.Result;
import com.dongl.user.dao.AdminDao;
import com.dongl.user.entity.Admin;
import com.dongl.user.config.BCryptUtil;
import com.dongl.utils.IdWorker;
import com.dongl.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private BCryptUtil bCryptUtil;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Admin> findAll() {
        return adminDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Admin> findSearch(Map whereMap, int page, int size) {
        Specification<Admin> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return adminDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Admin> findSearch(Map whereMap) {
        Specification<Admin> specification = createSpecification(whereMap);
        return adminDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Admin findById(String id) {
        return adminDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param admin
     */
    public void add(Admin admin) {

        admin.setId(idWorker.nextId() + "");
        String password = admin.getPassword();
        // 1. 通过 SpringSecurity 密码加密
        String encoderPassword = bCryptUtil.encoder(password);
        admin.setPassword(encoderPassword);
        adminDao.save(admin);
    }

    /**
     * 修改
     *
     * @param admin
     */
    public void update(Admin admin) {
        adminDao.save(admin);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        adminDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Admin> createSpecification(Map searchMap) {

        return new Specification<Admin>() {

            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 登陆名称
                if (searchMap.get("loginname") != null && !"".equals(searchMap.get("loginname"))) {
                    predicateList.add(cb.like(root.get("loginname").as(String.class), "%" + (String) searchMap.get("loginname") + "%"));
                }
                // 密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public Result login(Map<String, Object> map) {

        // 1.获取参数
        String loginName = String.valueOf(map.get("loginName"));
        String password = String.valueOf(map.get("password"));

        // 2.查询用户是否存在
        Admin admin = adminDao.findByLoginName(loginName);
        if (null == admin) {
            return Result.error("用户不存在！");
        }

        String encodedPassword = admin.getPassword();
        // 3.通过  SpringSecurity 进行密码验证
        Boolean bool = bCryptUtil.matches(password, encodedPassword);
        // 4. 密码正确返回：true
        if (!bool) {
            return Result.error("账户或密码错误！");
        }

        // JWT 认证生成 Token ; admin：表示暂放入admin权限，动态暂未实现
        String token = jwtUtil.createJWT(admin.getId(),admin.getLoginName(),"admin");
        Map<String,Object> mapToken = new HashMap<>();
        mapToken.put("token",token);
        mapToken.put("admin",admin);

        return Result.success("登陆成功！", mapToken);
    }
}
