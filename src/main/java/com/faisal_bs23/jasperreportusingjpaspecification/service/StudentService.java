package com.faisal_bs23.jasperreportusingjpaspecification.service;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentFilter;
import com.faisal_bs23.jasperreportusingjpaspecification.entity.StudentEntity;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpa.StudentRepository;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpaspecification.SearchCriteria;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpaspecification.SearchOperation;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpaspecification.StudentSpecification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }


  public List<StudentEntity> getStudents(String year,String semester,String section,Integer creditCompleted){
    var filter = new StudentFilter(year,semester,section,creditCompleted);
    var specification = new StudentSpecification();
    specification.applyFilter(filter);

    return studentRepository.findAll(specification);
  }
}
