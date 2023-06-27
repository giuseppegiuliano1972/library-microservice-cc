package com.la.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringBootApplication
//@EnableFeignClients
//@EnableDiscoveryClient
public class LibraryAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAuthApplication.class, args);
	}
	
//	@Bean
//	public FilterRegistrationBean<AuthTokenFilter> authFilter(){
//	    FilterRegistrationBean<AuthTokenFilter> registrationBean 
//	      = new FilterRegistrationBean<>();
//	        
//	    registrationBean.setFilter(new AuthTokenFilter());
//	    registrationBean.addUrlPatterns("/auth/*");
//	    registrationBean.setOrder(2);
//	        
//	    return registrationBean;    
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
	    return new BCryptPasswordEncoder();
	}
}
