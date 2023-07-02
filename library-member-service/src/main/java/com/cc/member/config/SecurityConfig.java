package com.cc.member.config;

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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
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
	                	logger.info("ADD CORS MAPP");
	                    registry.addMapping(controller)
	                            .allowedOriginPatterns("*")
	                            .allowedOrigins("*")
	                            .allowedHeaders("Authorization", "Content-Type", "authorization","x-auth-token","chiave")
	                            .exposedHeaders("x-auth-token","Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
	                            .allowedMethods("OPTIONS", "PUT", "POST", "DELETE", "GET");
	               }
	            }
	        };
	    }
	    
	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	http.cors().and().csrf().disable();
	   
	    	return http.build();
//	        http
//	        .cors().and()
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeHttpRequests((authz) -> authz
//                    .requestMatchers(HttpMethod.GET, "/member/health").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/member/cerca/**").permitAll()
//                    .requestMatchers(HttpMethod.POST, "/member/addmember").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
//            .headers().cacheControl();
//
//	        return http.build();
	    }
	
}