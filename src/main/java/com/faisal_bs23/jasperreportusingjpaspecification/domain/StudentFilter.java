package com.faisal_bs23.jasperreportusingjpaspecification.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class StudentFilter {
  private String studentId;
  private String studentName;
  private String admissionYear;
  private String currentSemester;
  private String currentSection;
  private String currentCgpa;
  private Integer creditCompleted;

  public StudentFilter(String admissionYear, String currentSemester, String currentSection, Integer creditCompleted) {
    this.admissionYear = admissionYear;
    this.currentSemester = currentSemester;
    this.currentSection = currentSection;
    this.creditCompleted = creditCompleted;
  }
}
