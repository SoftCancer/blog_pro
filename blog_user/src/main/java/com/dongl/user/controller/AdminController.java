package com.dongl.user.controller;

import java.util.Map;

import com.dongl.entity.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongl.user.entity.Admin;
import com.dongl.user.service.AdminService;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return Result.success("查询成功", adminService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return Result.success("查询成功", adminService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
        return Result.successPage(pageList);
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return Result.success("查询成功", adminService.findSearch(searchMap));
    }

    /**
     * 账户注册
     *
     * @param admin
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Admin admin) {
        String loginName = admin.getLoginName();
        String password = admin.getPassword();
        if (StringUtils.isEmpty(loginName)) {
            return Result.error("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return Result.error("密码不能为空");
        }
        // 1. 判断用户是否已存在
        Admin adminExis = adminService.findById(loginName);
        if (null != adminExis) {
            return Result.error("用户名已存在，请换个用户名重新注册！");
        }
        adminService.add(admin);
        return Result.success("增加成功");
    }

    /**
     * 修改
     *
     * @param admin
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Admin admin, @PathVariable String id) {
        admin.setId(id);
        adminService.update(admin);
        return Result.success("修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        adminService.deleteById(id);
        return Result.success("删除成功");
    }

    /**
     * {"loginName":"admin","password":"123456","state":"1"}
     *
     * @Description:
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 22:26
     **/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map map) {
        Object password = map.get("password");
        Object loginName = map.get("loginName");
        if (null == loginName || "".equals(loginName)) {
            return Result.error("用户名不能为空！");
        }
        if (null == password || "".equals(password)) {
            return Result.error("密码不能为空！");
        }

        Result result = adminService.login(map);
        return result;
    }

}
