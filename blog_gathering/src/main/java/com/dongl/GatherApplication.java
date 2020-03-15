package com.dongl;
import com.dongl.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching  //开启 SpringCache 缓存
@SpringBootApplication
public class GatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatherApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
