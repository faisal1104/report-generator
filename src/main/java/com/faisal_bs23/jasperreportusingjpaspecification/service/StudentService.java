package com.faisal_bs23.jasperreportusingjpaspecification.service;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentDomain;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentFilter;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.dynamic.DynamicReport;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpa.StudentRepository;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpaspecification.StudentSpecification;
import com.faisal_bs23.jasperreportusingjpaspecification.service.report.dynamic.DynamicReportService;
import com.faisal_bs23.jasperreportusingjpaspecification.util.StudentMapper;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;
  private final DynamicReportService dynamicReportService;

  public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, DynamicReportService dynamicReportService) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
    this.dynamicReportService = dynamicReportService;
  }

  public List<StudentDomain> getStudents(String year, String semester, String section, Integer creditCompleted){
    var filter = new StudentFilter(year,semester,section,creditCompleted);
    var specification = new StudentSpecification();
    specification.applyFilter(filter);

    return studentRepository.findAll(specification).stream()
        .map(e->studentMapper.entityToDomain().map(e)).collect(Collectors.toList());
  }

  public void exportStudentReport(String year, String semester, String section, Integer creditCompleted, ExportType exType, HttpServletResponse response) throws IOException, JRException {
    var filter = new StudentFilter(year,semester,section,creditCompleted);
    filter.setExportType(exType);
    var specification = new StudentSpecification();
    specification.applyFilter(filter);
    var list = studentRepository.findAll(specification).stream()
                                              .map(e->studentMapper.entityToDomain().map(e)).collect(Collectors.toList());
    var inputStream = filter.getInputStream();
    var title = filter.getReportTitle();
    var exportType = filter.getExportType();
    var report = new DynamicReport(inputStream,title,exportType,response,filter.generateDynamicColumnAndRows(list));
    dynamicReportService.export(report);
  }

}
