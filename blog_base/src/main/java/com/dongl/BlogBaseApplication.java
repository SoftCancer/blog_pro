package com.dongl;

import com.dongl.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Hello world!
 */
@SpringBootApplication
@CrossOrigin   // 跨域
public class BlogBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogBaseApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
