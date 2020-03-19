package com.dongl.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongl.user.entity.Admin;

import java.util.Map;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface AdminDao extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {

    /**
     * @Description: 根据用户名获取用户信息
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 23:02
     **/
    public Admin findByLoginName(String loginName);
}
