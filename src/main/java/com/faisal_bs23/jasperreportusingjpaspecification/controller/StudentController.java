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

  @GetMapping("/get")
  public ResponseEntity<?> getAllStudents(
      @RequestParam(value = "year", required = false) final String year,
      @RequestParam(value = "semester", required = false) final String semester,
      @RequestParam(value = "section", required = false) final String section,
      @RequestParam(value = "creditCompleted", required = false) final Integer creditCompleted){
    return ResponseEntity.ok(studentService.getStudents(year, semester, section, creditCompleted));
  }
}