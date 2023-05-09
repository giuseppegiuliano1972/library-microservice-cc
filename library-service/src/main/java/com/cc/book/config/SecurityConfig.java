package com.cc.book.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

@Configuration
public class SecurityConfig  {
	   
	   private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	   private Set<String> controllers ;


	    @Bean
	    public WebMvcConfigurer configurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                    registry.addMapping("/**")
	                            .allowedOrigins("http://localhost:4200");
	                            //.allowedHeaders("Authorization", "Content-Type")
	                            //.allowedMethods("OPTIONS", "PUT", "POST", "DELETE", "GET");
	                }
	        };
	    }
	    
	    
	
}