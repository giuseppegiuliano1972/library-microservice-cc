package com.cc.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.cc")
public class LibraryMemberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMemberServiceApplication.class, args);
	}

}
