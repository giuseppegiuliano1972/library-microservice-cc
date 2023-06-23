package com.ls.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ls.library.filter.RequestContextFilter;

import jakarta.servlet.Filter;

@Configuration
public class WebStarterConfiguration {

  @Value("${req.msg.prefix:[}")
  private String messagePrefix;
  @Value("${req.msg.suffix:]}")
  private String messageSuffix;
  @Value("${req.inc.payload:true}")
  private boolean includePayload;
  @Value("${req.inc.query.string:true}")
  private boolean includeQueryString;
  @Value("${req.inc.client.info:true}")
  private boolean includeClientInfo;
  @Value("${req.max.payload.length:5120}")
  private int maxPayloadLength;

  @Bean
  @ConditionalOnMissingBean(RequestContextFilter.class)
  public Filter reqContextFilter() {
    RequestContextFilter requestContextFilter = new RequestContextFilter();
    requestContextFilter.setIncludeHeaders(true);
    requestContextFilter.setAfterMessagePrefix(messagePrefix);
    requestContextFilter.setAfterMessageSuffix(messageSuffix);
    requestContextFilter.setIncludePayload(includePayload);
    requestContextFilter.setIncludeQueryString(includeQueryString);
    requestContextFilter.setIncludeClientInfo(includeClientInfo);
    requestContextFilter.setMaxPayloadLength(maxPayloadLength);
    return requestContextFilter;
  }
}