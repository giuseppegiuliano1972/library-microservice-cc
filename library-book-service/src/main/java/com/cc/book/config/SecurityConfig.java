package com.cc.book.config;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig  {
	   
	 private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	   private Set<String> controllers ;

	   @Autowired
	    public void CorsConfig(ApplicationContext applicationContext) {
	        controllers = Stream.of(applicationContext.getBeanNamesForAnnotation(CrossOrigin.class))
	                .filter(Objects::nonNull)
	                .map(bean -> applicationContext.findAnnotationOnBean(bean, RequestMapping.class))
	                .filter(mapping -> Objects.nonNull(mapping) && mapping.path().length > 0)
	                .map(mapping -> mapping.path()[0])
	                .peek(path -> logger.info(format("\"%s\" path registered for CORS configuration.", path)))
	                .collect(toSet());
	    }

	    @Bean
	    public WebMvcConfigurer configurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                for (String controller : controllers) {
	                    registry.addMapping(controller)
	                            .allowedOriginPatterns("*")
	                            .allowedHeaders("Authorization", "Content-Type")
	                            .allowedMethods("OPTIONS", "PUT", "POST", "DELETE", "GET");
	                }
	            }
	        };
	    }
	
	
}