package com.ls.library.payload.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Data
public class RequestContext {

  private String reqInfo;
  private boolean failed;
  private String errorMessage;
  private String errorCode;
}
