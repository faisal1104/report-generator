package com.faisal.reportgeneratorusingjasper.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public <E extends GlobalCustomException> ResponseEntity<Object> handleExceptions(E exception, WebRequest webRequest) {
    var response = new MyCustomExceptionRespone();
    response.setDateTime(LocalDateTime.now());
    response.setMessage(exception.getMessage());
    response.setServerStatusCode(exception.getHttpStatus().toString());
    ResponseEntity<Object> entity = new ResponseEntity<>(response, exception.getHttpStatus());
    return entity;
  }
}
