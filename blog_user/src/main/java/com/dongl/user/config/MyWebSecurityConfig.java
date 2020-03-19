package com.dongl.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: SpringSecurity 的配置类
 * @author: YaoGuangXun
 * @date: 2020/3/19 18:46
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * @Description: 重新父类的 configure 方法
     * @Author: YaoGuangXun
     * @Date: 2020/3/19 18:48
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

//        super.configure(http);
    }


}
