package com.dongl;

import com.dongl.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/22 1:09
 * @Version: 1.0
 */
@SpringBootApplication
public class BlogFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogFriendApplication.class,args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
