package com.ls.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

//@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
@Slf4j
public class LibraryLendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryLendingApplication.class, args);
		
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
}
