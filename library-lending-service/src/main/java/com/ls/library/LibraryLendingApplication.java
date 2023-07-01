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
		
//		 val decodedJWT =
//			        JWT.decode(
//			            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJjcmVhdGVkQXRcIjpcIjIwMjAtMDUtMzBUMTc6MDY6MDIuMDgwKzAwMDBcIixcInVzZXJBY2NvdW50SWRcIjpcIkFDQzgwNzI5MTU1ODYzOFwiLFwiaXNTdWNjZXNzXCI6dHJ1ZSxcInNlc3Npb25JZFwiOlwiOWQwMGYwZGQtN2VmMi00ZTE2LTk3OWEtYTVlMDU1MDQwNWE3XCIsXCJpcEFkZHJlc3NcIjpcIjEyMi4xNzEuNjUuMjA5LFwiLFwiZGV2aWNlSWRcIjpudWxsLFwic3VwZXJBY2NvdW50SWRcIjpcIkFDQzgwNzI5MTU1ODYzOFwifSIsIm5iZiI6MTU5MDg1ODMxMiwiaXNzIjoiZ3Jvd3diaWxsaW9ubWlsbGVubmlhbCIsImV4cCI6MTU5MzQ1MDM2MiwiaWF0IjoxNTkwODU4MzYyfQ.HfcUG-FbwPQ_eWCa5Lr6gFIfzYC8eShokZgJFVEIMq_DujLkjXma0ss_qLk4uvcOCWm6cen_yEZBi1ZTYOg4");
//			    log.info("Issuer {}", decodedJWT.getIssuer());
//			    log.info("Subject {}", decodedJWT.getSubject());
//			    log.info("Issued at {}", decodedJWT.getSignature());
//			    log.info("Payload {}", decodedJWT.getPayload());
//			    log.info("Issued At {}", decodedJWT.getIssuedAt());
//			    log.info("Not before {}", decodedJWT.getNotBefore());
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
}
