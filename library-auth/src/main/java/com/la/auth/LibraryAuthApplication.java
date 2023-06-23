package com.la.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;


//@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringBootApplication
//@EnableFeignClients
//@EnableDiscoveryClient
public class LibraryAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAuthApplication.class, args);
	}

}
