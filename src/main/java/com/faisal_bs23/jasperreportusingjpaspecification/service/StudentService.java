package com.faisal_bs23.jasperreportusingjpaspecification.service;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentDomain;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentFilter;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.StudentGroupBy;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.dynamic.DynamicReport;
import com.faisal_bs23.jasperreportusingjpaspecification.entity.StudentEntity;
import com.faisal_bs23.jasperreportusingjpaspecification.exception_handler.GlobalCustomException;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpa.StudentRepository;
import com.faisal_bs23.jasperreportusingjpaspecification.repository.jpaspecification.StudentSpecification;
import com.faisal_bs23.jasperreportusingjpaspecification.service.report.dynamic.DynamicReportService;
import com.faisal_bs23.jasperreportusingjpaspecification.util.StudentMapper;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;
  private final DynamicReportService dynamicReportService;
  private final EntityManager entityManager;

  public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, DynamicReportService dynamicReportService, EntityManager entityManager) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
    this.dynamicReportService = dynamicReportService;
    this.entityManager = entityManager;
  }

  public List<StudentDomain> getStudents(String year, String semester, String section, Integer creditCompleted){
    var filter = new StudentFilter(year,semester,section,creditCompleted);
    var specification = new StudentSpecification();
    specification.applyFilter(filter);

    return studentRepository.findAll(specification).stream()
        .map(e->studentMapper.entityToDomain().map(e)).collect(Collectors.toList());
  }

  public void exportStudentReport(String year, String semester, String section, Integer creditCompleted, ExportType exType, HttpServletResponse response) throws IOException, JRException {
    if(exType==null)
      throw new GlobalCustomException("ExportType parameter is missing.", HttpStatus.BAD_REQUEST);
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

  public List<StudentDomain> getStudentByCriteraApi(
      StudentFilter filter) {

    var groupBy = filter.getGroupByList();
    if (groupBy == null) {
      throw new GlobalCustomException("At least provide one groupBy to query", HttpStatus.BAD_REQUEST);
    }
    var set = new HashSet<>(groupBy);
    if (set.size() < groupBy.size())
      throw new GlobalCustomException("Duplicates groupBy can't be applied", HttpStatus.BAD_REQUEST);

    List<StudentDomain> studentDomainList = new ArrayList<>();
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
    Root<StudentEntity> root = query.from(StudentEntity.class);
    List<Predicate> predicateList = new ArrayList<>();
    List<Selection<?>> rootList = new ArrayList<>();
    List<Expression<?>> groupByList = new ArrayList<>();
    List<Order> orderByList = new ArrayList<>();

    // dynamic group by
    for (var g : groupBy) {
      applyGroupBy(root, cb, rootList, groupByList, orderByList, g);
    }

    // apply filter
    if (filter.getAdmissionYear() != null)
      predicateList.add(cb.equal(root.get("admissionYear"), filter.getAdmissionYear()));
    if (filter.getCurrentSemester() != null)
      predicateList.add(cb.equal(root.get("currentSemester"), filter.getCurrentSemester()));
    if (filter.getCurrentSection() != null)
      predicateList.add(cb.equal(root.get("currentSection"), filter.getCurrentSection()));
    if (filter.getCurrentCgpa() != null)
      predicateList.add(cb.equal(root.get("currentCgpa"), filter.getCurrentCgpa()));

    // common
    rootList.add(cb.count(root));
    query.multiselect(rootList);
    query.groupBy(groupByList);
    query.orderBy(orderByList);
    query.where(predicateList.toArray(new Predicate[0]));
    TypedQuery<Object[]> q = entityManager.createQuery(query);

    List<Object[]> resultList = q.getResultList();
    resultList.forEach(
        objects -> {
          var student = new StudentDomain();

          int index = 0;

          // dynamic
          for (var g : groupBy) {
            switch (g) {
              case ADMISSION_YEAR:
                var admissionYear = objects[index] != null ? objects[index].toString() : null;
                student.setAdmissionYear(admissionYear);
                break;
              case SEMESTER:
                var semester = objects[index] != null ? objects[index].toString() : null;
                student.setCurrentSemester(semester);
                break;
              case SECTION:
                var section = objects[index] != null ? objects[index].toString() : null;
                student.setCurrentSection(section);
                break;
              case CGPA:
                var cgpa = objects[index] != null ? objects[index].toString() : null;
                student.setCurrentCgpa(cgpa);
                break;
            }
            index++;
          }

          // static
          student.setStudentCount(Integer.valueOf(objects[objects.length - 1].toString()));

          studentDomainList.add(student);
        });

    return studentDomainList;
  }

  private void applyGroupBy(
      Root<StudentEntity> root,
      CriteriaBuilder cb,
      List<Selection<?>> rootList,
      List<Expression<?>> groupByList,
      List<Order> orderByList,
      StudentGroupBy groupBy) {
    switch (groupBy) {
      case ADMISSION_YEAR:
        rootList.add(root.get("admissionYear"));
        groupByList.add(root.get("admissionYear"));
        orderByList.add(cb.asc(root.get("admissionYear")));
        break;

      case SEMESTER:
        rootList.add(root.get("currentSemester"));
        groupByList.add(root.get("currentSemester"));
        orderByList.add(cb.asc(root.get("currentSemester")));
        break;

      case SECTION:
        rootList.add(root.get("currentSection"));
        groupByList.add(root.get("currentSection"));
        orderByList.add(cb.asc(root.get("currentSection")));
        break;

      case CGPA:
        rootList.add(root.get("currentCgpa"));
        groupByList.add(root.get("currentCgpa"));
        orderByList.add(cb.asc(root.get("currentCgpa")));
        break;


      default:
        throw new GlobalCustomException("GroupBy parameter not found", HttpStatus.NOT_FOUND);
    }
  }

  public void exportStudentGroupByReport(StudentFilter filter, HttpServletResponse response) throws IOException, JRException {
    if(filter.getExportType() == null)
      throw new GlobalCustomException("ExportType parameter is missing.", HttpStatus.BAD_REQUEST);
    var list = getStudentByCriteraApi(filter);
    var inputStream = filter.getInputStream();
    var title = filter.getReportTitle();
    var exportType = filter.getExportType();
    var report = new DynamicReport(inputStream,title,exportType,response,filter.generateDynamicColumnAndRows(list));
    dynamicReportService.export(report);
  }
}
