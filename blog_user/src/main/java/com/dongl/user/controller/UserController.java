package com.dongl.user.controller;
import java.util.Map;

import com.dongl.entity.Result;
import com.dongl.utils.RegexUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongl.user.entity.User;
import com.dongl.user.service.UserService;

/**
 * 用户控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return Result.success("查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return Result.success("查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  Result.successPage(pageList);
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return Result.success("查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return Result.success("增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return Result.success("修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return Result.success("删除成功");
	}

	/**
	 * @Description: 发送验证码
	 * @Author: YaoGuangXun
	 * @Date: 2020/3/18 16:25
	 **/
	@RequestMapping(value = "/sms/{mobile}",method = RequestMethod.POST)
	public Result sendShortMessage(@PathVariable String mobile){
	    // 验证手机号码格式
        if (!RegexUtils.isMobileExact(mobile)){
            return Result.error("手机号码格式不正确！");
        }

	    if (StringUtils.isEmpty(mobile)){
	        return Result.error("手机号不能为空！");
        }
        Result result =userService.sendShortMessage(mobile);
        return result;
    }

    /**
     * @Description: 用户注册
     * @Author: YaoGuangXun
     * @Date: 2020/3/18 16:25
     **/
	@RequestMapping(value = "register/{code}",method = RequestMethod.POST)
	public Result regist (@RequestBody User user,@PathVariable String code){
        if (StringUtils.isEmpty(code)){
            return Result.error("验证码不能为空！");
        }
        Result result =userService.regist(user,code);
        return result;
    }

}
