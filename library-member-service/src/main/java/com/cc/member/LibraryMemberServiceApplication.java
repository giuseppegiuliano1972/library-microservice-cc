package com.cc.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LibraryMemberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMemberServiceApplication.class, args);
	}

}
