package com.cc.book.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties(prefix = "service")
@EnableConfigurationProperties
@Slf4j
@Setter
@Getter
public class ServiceAuthConfig {

  private String jwtAlgorithmKey;

  private List<String> registeredSecretKeys;
}