package com.faisal_bs23.jasperreportusingjpaspecification.service;

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

  public List<StudentEntity> getAllStudents() {
    return studentRepository.findAll();
  }

  public List<StudentEntity> getByCondition(String year){
    System.out.println(year);

    var filter = new StudentSpecification();
    filter.addSearchCriteria(new SearchCriteria("admissionYear", year, SearchOperation.EQUAL));
    var studentEntities = studentRepository.findAll(filter);
    studentEntities.forEach(System.out::println);

    return studentEntities;
  }
}
