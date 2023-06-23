package com.ls.library.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import com.ls.library.payload.request.RequestContext;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestContextFilter extends AbstractRequestLoggingFilter {

  @Autowired
  private RequestContext requestContext;

  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {
    requestContext.setReqInfo(message);
  }

  @Override
  protected void afterRequest(HttpServletRequest request, String message) {
    // concat before request and after request messages.
    String reqInfo = StringUtils.isEmpty(requestContext.getReqInfo()) ?
        requestContext.getReqInfo().concat(message) : message;

    requestContext.setReqInfo(reqInfo);

    if (requestContext.isFailed()) {
      log.error("Request processing failed, requestInfo={}", requestContext);
    }
  }
}