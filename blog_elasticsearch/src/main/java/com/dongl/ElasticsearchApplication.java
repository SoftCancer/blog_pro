package com.dongl;

import com.dongl.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/17 13:30
 * @Version: 1.0
 */
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(ElasticsearchApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
