package com.faisal_bs23.jasperreportusingjpaspecification.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor@AllArgsConstructor
@Getter
public class GlobalCustomException extends RuntimeException{
  private String message;
  private HttpStatus httpStatus;
}
