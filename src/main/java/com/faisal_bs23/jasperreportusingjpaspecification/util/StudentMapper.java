package com.faisal_bs23.jasperreportusingjpaspecification.util;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentDomain;
import com.faisal_bs23.jasperreportusingjpaspecification.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

  public ObjectMapper<StudentEntity, StudentDomain> entityToDomain(){
    return entity-> new StudentDomain(){
      {
        setStudentId(entity.getStudentId());
        setStudentName(entity.getStudentName());
        setAdmissionYear(entity.getAdmissionYear());
        setCurrentSemester(entity.getCurrentSemester());
        setCurrentSection(entity.getCurrentSection());
        setCurrentCgpa(entity.getCurrentCgpa());
        setCreditCompleted(entity.getCreditCompleted());
      }
    };
  }
  public ObjectMapper<StudentDomain,StudentEntity> domainToEntity(){
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
  }


}
