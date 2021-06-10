package com.faisal.reportgeneratorusingjasper.mapper;

import com.faisal.reportgeneratorusingjasper.domain.StudentResponse;
import com.faisal.reportgeneratorusingjasper.persistence.entity.StudentEntity;

public class StudentMapper {

  public static StudentResponse entityToDomain(StudentEntity entity){
    return new StudentResponse()
        .setStudentId(entity.getStudentId())
    .setStudentName(entity.getStudentName())
    .setAdmissionYear(entity.getAdmissionYear())
    .setCurrentSemester(entity.getCurrentSemester())
    .setCurrentSection(entity.getCurrentSection())
    .setCurrentCgpa(entity.getCurrentCgpa())
    .setCreditCompleted(entity.getCreditCompleted());
  }
/*  public ObjectMapper<StudentResponse,StudentEntity> domainToEntity(){
    return domain-> new StudentEntity(){
      {
        setStudentId(domain.getStudentId());
        setStudentName(domain.getStudentName());
        setAdmissionYear(domain.getAdmissionYear());
        setCurrentSemester(domain.getCurrentSemester());
        setCurrentSection(domain.getCurrentSection());
        setCurrentCgpa(domain.getCurrentCgpa());
        setCreditCompleted(domain.getCreditCompleted());
      }
    };
  }*/


}
