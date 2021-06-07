package com.faisal_bs23.jasperreportusingjpaspecification.controller;

import com.faisal_bs23.jasperreportusingjpaspecification.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllStudents(){
    return ResponseEntity.ok(studentService.getAllStudents());
  }
  @GetMapping("find")
  public ResponseEntity<?> getAllStudentsBy(@RequestParam(value = "year") final String year){
    return ResponseEntity.ok(studentService.getByCondition(year));
  }
}
