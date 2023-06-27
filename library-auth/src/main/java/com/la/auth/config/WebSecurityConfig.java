package com.la.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.la.auth.jwt.AuthEntryPointJwt;
import com.la.auth.jwt.AuthTokenFilter;
import com.la.auth.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.authorizeRequests()
	        		.requestMatchers("/auth/**").permitAll()
	        		.requestMatchers("/login/**").permitAll()
	                    .anyRequest().authenticated()
	                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                //.and().oauth2ResourceServer()
	                //    .jwt().jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter()).and()
	                .and().cors() // by default uses a Bean by the name of corsConfigurationSource
	                .and().csrf().disable()
	                .formLogin().disable()
	                .httpBasic().disable()
	                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
	                .build();
	    }
	
}
