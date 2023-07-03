package com.la.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;


@Configuration
@ConfigurationProperties(prefix = "service")
@EnableConfigurationProperties
public class ServiceAuthConfig {
	 @Setter @Getter private String jwtAlgorithmKey;

	  @Setter @Getter private String serviceBSecretKey;

	  @Value("${spring.application.name}")
	  private String applicationName;

	  @Getter private String serviceBAuthToken;

	  /*
	    Generate auth token which will be passed in header when calling 
	    API of other services
	   */
	  @PostConstruct
	  public void load() {
	    serviceBAuthToken = createJwtToken(serviceBSecretKey);
	  }

	  /*
	   Create JWT Token with the algorithm key (common for both the called and caller service)
	  */
	  private String createJwtToken(String serviceSecretKey) {
	    return JWT.create()
	        .withIssuer(applicationName)
	        .withSubject(serviceSecretKey)
	        .sign(Algorithm.HMAC512(jwtAlgorithmKey));
	  }
}
