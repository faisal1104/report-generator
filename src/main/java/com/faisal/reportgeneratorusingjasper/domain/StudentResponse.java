package com.faisal.reportgeneratorusingjasper.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @NoArgsConstructor@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
@ApiModel(description = "Student response")
public class StudentResponse {
  private String studentId;
  private String studentName;
  private String admissionYear;
  private String currentSemester;
  private String currentSection;
  private String currentCgpa;
  private Integer creditCompleted;
}
