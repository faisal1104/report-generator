package com.faisal.reportgeneratorusingjasper.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "student_information")
@Getter@Setter@NoArgsConstructor
@Accessors(chain = true)
public class StudentEntity implements Serializable {
  @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String studentId;
  private String studentName;
  private String admissionYear;
  private String currentSemester;
  private String currentSection;
  private String currentCgpa;
  private Integer creditCompleted;

  public StudentEntity(String studentId, String studentName, String admissionYear, String currentSemester, String currentSection, String currentCgpa, int creditCompleted) {
    this.studentId = studentId;
    this.studentName = studentName;
    this.admissionYear = admissionYear;
    this.currentSemester = currentSemester;
    this.currentSection = currentSection;
    this.currentCgpa = currentCgpa;
    this.creditCompleted = creditCompleted;
  }
}
