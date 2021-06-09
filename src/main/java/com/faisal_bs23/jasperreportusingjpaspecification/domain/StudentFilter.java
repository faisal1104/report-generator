package com.faisal_bs23.jasperreportusingjpaspecification.domain;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.dynamic.DynamicReportProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentGroupBy.*;
import static com.faisal_bs23.jasperreportusingjpaspecification.util.Constant.EXTENSION_JRXML;
import static com.faisal_bs23.jasperreportusingjpaspecification.util.Constant.FOLDER_PATH_REPORT_DYNAMIC;

@Data@NoArgsConstructor
public class StudentFilter {
  private String studentId;
  private String studentName;
  private String admissionYear;
  private String currentSemester;
  private String currentSection;
  private String currentCgpa;
  private Integer creditCompleted;
  private ExportType exportType;
  private Integer studentCounter;
  private List<StudentGroupBy> groupByList;

  public StudentFilter(String admissionYear, String currentSemester, String currentSection, String currentCgpa, ExportType exportType, List<StudentGroupBy> groupByList) {
    this.admissionYear = admissionYear;
    this.currentSemester = currentSemester;
    this.currentSection = currentSection;
    this.currentCgpa = currentCgpa;
    this.exportType = exportType;
    this.groupByList = groupByList;
  }

  public StudentFilter(String admissionYear, String currentSemester, String currentSection, Integer creditCompleted) {
    this.admissionYear = admissionYear;
    this.currentSemester = currentSemester;
    this.currentSection = currentSection;
    this.creditCompleted = creditCompleted;
  }

  public InputStream getInputStream() {
    var name =
        FOLDER_PATH_REPORT_DYNAMIC
            + exportType.toString().toLowerCase()
            + EXTENSION_JRXML;
    return getClass().getResourceAsStream(name);
  }

  public String getReportTitle() {
    return groupByList!= null? "Student Group By List" : "Student List";
  }

  public DynamicReportProperties generateDynamicColumnAndRows(List<StudentDomain> list) {

    List<String> columnHeaders = new ArrayList<>();
    List<Integer> indexesOfColumnTypeNumber = new ArrayList<>();
    List<List<String>> rows = new ArrayList<>();
    List<String> summary = new ArrayList<>();
    studentCounter = 0;

    if(groupByList != null){
      //dynamically add header
      for (var g : groupByList) {
        columnHeaders.add(g.getDescription());
      }
      //static header
      columnHeaders.add("Student Count");
    }else {
      columnHeaders.add("Student Id");
      columnHeaders.add("Student Name");
      columnHeaders.add("Admission Year");
      columnHeaders.add("Current Semester");
      columnHeaders.add("Current Section");
      columnHeaders.add("Current CGPA");
      columnHeaders.add("Credit Completed");
    }

    for (var student : list) {
      List<String> row = new ArrayList<>();
      if(groupByList != null)
        addRowForGroupBy(row, student);
      else {
        row.add(student.getStudentId());
        row.add(student.getStudentName());
        row.add(student.getAdmissionYear());
        row.add(student.getCurrentSemester());
        row.add(student.getCurrentSection());
        row.add(student.getCurrentCgpa());
        row.add(student.getCreditCompleted().toString());
      }
      rows.add(row);
    }
    // summary
    summary.add("Total Student");
    if(groupByList != null)
      summary.add(String.valueOf(studentCounter));
    else
      summary.add(String.valueOf(list.size()));

    // number field index list
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 2); // "Total"
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 1); // studentNumber

    return new DynamicReportProperties(columnHeaders, indexesOfColumnTypeNumber, rows, summary);
  }
  void addRowForGroupBy (List<String> row, StudentDomain student) {
    for (var groupBy: groupByList) {
      switch (groupBy) {
        case SEMESTER:
          row.add(student.getCurrentSemester());
          break;
        case SECTION:
          row.add(student.getCurrentSection());
          break;
        case ADMISSION_YEAR:
          row.add(student.getAdmissionYear());
          break;
        case CGPA:
          row.add(student.getCurrentCgpa());
          break;
      }
    }
    row.add(student.getStudentCount().toString());
    studentCounter+=student.getStudentCount();
  }
}
