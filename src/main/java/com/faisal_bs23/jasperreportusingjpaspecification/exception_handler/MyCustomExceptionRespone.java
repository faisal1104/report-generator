package com.faisal_bs23.jasperreportusingjpaspecification.exception_handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
public class MyCustomExceptionRespone {

  private String message;
  @JsonProperty("time stamp")
  private LocalDateTime dateTime;
  @JsonProperty("server status code")
  private String serverStatusCode;

}
