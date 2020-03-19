package com.dongl.user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description: 封装SpringSecurity 加密及密码验证功能
 * @author: YaoGuangXun
 * @date: 2020/3/19 20:15
 * @Version: 1.0
 */
@Component
public class BCryptUtil {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptUtil(){
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * @Description: 加密
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 20:18
     **/
    public String encoder(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * @Description: 验证加密 ,密码正确返回：true，否则返回：false
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 20:20
     **/
    public Boolean matches(String password,String salt){
        return bCryptPasswordEncoder.matches(password,salt);
    }
}
