package com.faisal_bs23.jasperreportusingjpaspecification.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StudentGroupBy {
  ADMISSION_YEAR("Admission Year"),
  SEMESTER("Current Semester"),
  SECTION("Current Section"),
  CGPA("Current CGPA");

  private final String description;
}
