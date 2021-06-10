package com.faisal.reportgeneratorusingjasper.controller;

import com.faisal.reportgeneratorusingjasper.domain.StudentFilter;
import com.faisal.reportgeneratorusingjasper.domain.StudentGroupBy;
import com.faisal.reportgeneratorusingjasper.domain.StudentResponse;
import com.faisal.reportgeneratorusingjasper.domain.report.ExportType;
import com.faisal.reportgeneratorusingjasper.exceptionhandler.GlobalCustomException;
import com.faisal.reportgeneratorusingjasper.service.StudentService;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
public class StudentReportController implements StudentReportApi {

  private final StudentService studentService;

  public StudentReportController(StudentService studentService) {
    this.studentService = studentService;
  }

  @Override
  public ResponseEntity<List<StudentResponse>> getAllStudentList(
      String year, String semester, String section, Integer creditCompleted) {
    var filter = new StudentFilter()
        .setAdmissionYear(year).setCurrentSemester(semester).setCurrentSection(section)
        .setCreditCompleted(creditCompleted);
    return ResponseEntity.ok(studentService.getStudents(filter));
  }

  @Override
  public ResponseEntity<Void> exportAllStudentReport(
      String year, String semester, String section, Integer creditCompleted, ExportType exportType, HttpServletResponse response) throws IOException, JRException {

    var filter = new StudentFilter()
        .setAdmissionYear(year).setCurrentSemester(semester).setCurrentSection(section)
        .setCreditCompleted(creditCompleted);
    filter.setExportType(exportType);
    studentService.exportStudentReport(filter, response);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> exportStudentGroupByReport(
      List<StudentGroupBy> groupBy, String year, String semester, String section, ExportType exportType, HttpServletResponse response) throws IOException, JRException {

    if (Objects.isNull(groupBy) || CollectionUtils.isEmpty(groupBy))
      throw new GlobalCustomException("At least provide one groupBy to query", HttpStatus.BAD_REQUEST);

    var filter = new StudentFilter()
        .setGroupByList(groupBy)
        .setExportType(exportType)
        .setCurrentSemester(semester)
        .setAdmissionYear(year).setCurrentSection(section);
    studentService.exportStudentGroupByReport(filter, response);
    return ResponseEntity.ok().build();
  }
}
