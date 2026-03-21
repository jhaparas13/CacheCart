package com.paras.CacheCart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheCartApplication.class, args);
	}

}
