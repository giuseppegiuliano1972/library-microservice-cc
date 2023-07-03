package com.cc.member.filter;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cc.member.config.ServiceAuthConfig;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ServiceAuthFilter implements Filter {

  @Autowired 
  private ServiceAuthConfig serviceAuthConfig;

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequest);
    HttpServletResponse response = ((HttpServletResponse) servletResponse);
   
    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
    String serviceAuthToken = httpServletRequest.getHeader("SERVICE-AUTH-TOKEN");
    String authToken = httpServletRequest.getHeader("Authorization");
    log.info("authtoke",authToken);
    String cnttype = httpServletRequest.getHeader("Content-Type");
    boolean isTokenValid = validateJwtToken(serviceAuthToken);
//    if (!isTokenValid) {
//      HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//      httpServletResponse.setStatus(401);
//      httpServletResponse.getWriter().write("INVALID_TOKEN");
//    } else {
      filterChain.doFilter(servletRequest, servletResponse);
//    }
  }

  /*
   Validate the token
  */
  private boolean validateJwtToken(String jwtToken) {
    if (StringUtils.isEmpty(jwtToken)) {
      return false;
    }
    DecodedJWT decodedJWT =
        JWT.require(Algorithm.HMAC512(serviceAuthConfig.getJwtAlgorithmKey()))
            .build()
            .verify(jwtToken);
    log.info("Request Initiated from {}", decodedJWT.getIssuer());
    return serviceAuthConfig.getRegisteredSecretKeys().contains(decodedJWT.getSubject());
  }
}
