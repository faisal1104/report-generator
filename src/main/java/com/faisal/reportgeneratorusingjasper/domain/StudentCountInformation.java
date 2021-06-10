package com.faisal.reportgeneratorusingjasper.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Student count information")
public class StudentCountInformation {
    private String admissionYear;
    private String currentSemester;
    private String currentSection;
    private Integer count;
}
