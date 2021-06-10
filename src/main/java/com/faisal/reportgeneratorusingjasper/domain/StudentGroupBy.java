package com.faisal.reportgeneratorusingjasper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StudentGroupBy {
  ADMISSION_YEAR("Admission Year", 1),
  SEMESTER("Current Semester", 2),
  SECTION("Current Section", 3),
  //CGPA("Current CGPA"),

  ;
  private final String description;
  private final int order;
}
