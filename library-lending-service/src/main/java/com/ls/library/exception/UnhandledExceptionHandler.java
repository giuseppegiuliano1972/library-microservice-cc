package com.ls.library.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ls.library.payload.request.RequestContext;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
@Setter
public class UnhandledExceptionHandler {

  @Autowired
  private RequestContext requestContext;

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception ex) {
    log.error("Unhandled exception", ex);

    requestContext.setErrorMessage(ex.getMessage());
    // mark this request failed
    requestContext.setFailed(true);

    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }
}