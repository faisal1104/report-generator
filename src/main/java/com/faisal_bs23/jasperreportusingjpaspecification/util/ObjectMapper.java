package com.faisal_bs23.jasperreportusingjpaspecification.util;

@FunctionalInterface
public interface ObjectMapper<T,R> {
  R map(T value);
}
