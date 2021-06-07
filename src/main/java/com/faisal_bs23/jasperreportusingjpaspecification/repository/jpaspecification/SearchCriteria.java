package com.faisal_bs23.jasperreportusingjpaspecification.repository.jpaspecification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data @NoArgsConstructor @AllArgsConstructor
@Accessors(chain = true)
public class SearchCriteria {
  private String key; //entity field name
  private Object value; // paramter value
  private SearchOperation operation;

  public SearchCriteria(String key, SearchOperation operation) {
    this.key = key;
    this.operation = operation;
  }
}
