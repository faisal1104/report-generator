package com.faisal.reportgeneratorusingjasper.exceptionhandler;

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
