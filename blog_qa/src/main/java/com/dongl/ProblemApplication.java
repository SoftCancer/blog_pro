package com.dongl;
import com.dongl.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * @Description:
 * @Author: YaoGuangXun
 * @Date: 2020/3/15 13:26
 **/
@SpringBootApplication
public class ProblemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProblemApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
