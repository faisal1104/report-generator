package com.faisal.reportgeneratorusingjasper.service;

import com.faisal.reportgeneratorusingjasper.domain.StudentCountInformation;
import com.faisal.reportgeneratorusingjasper.domain.StudentFilter;
import com.faisal.reportgeneratorusingjasper.domain.StudentGroupBy;
import com.faisal.reportgeneratorusingjasper.domain.StudentResponse;
import com.faisal.reportgeneratorusingjasper.domain.report.dynamic.DynamicReport;
import com.faisal.reportgeneratorusingjasper.exceptionhandler.GlobalCustomException;
import com.faisal.reportgeneratorusingjasper.mapper.StudentMapper;
import com.faisal.reportgeneratorusingjasper.persistence.entity.StudentEntity;
import com.faisal.reportgeneratorusingjasper.persistence.repository.StudentRepository;
import com.faisal.reportgeneratorusingjasper.persistence.specification.StudentSpecification;
import com.faisal.reportgeneratorusingjasper.service.report.dynamic.DynamicReportService;
import com.faisal.reportgeneratorusingjasper.service.report.filler.StudentCountInfoRepostFiller;
import com.faisal.reportgeneratorusingjasper.service.report.filler.StudentInfoReportFiller;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final DynamicReportService dynamicReportService;
    private final EntityManager entityManager;
    private final StudentInfoReportFiller studentInfoReportFiller;
    private final StudentCountInfoRepostFiller studentCountInfoRepostFiller;

    public List<StudentResponse> getStudents(StudentFilter filter) {
        return studentRepository.findAll(StudentSpecification.apply(filter)).stream()
            .map(StudentMapper::entityToDomain).collect(Collectors.toList());
    }

    public void exportStudentReport(StudentFilter filter, HttpServletResponse response) throws IOException, JRException {
        var studentResponseList = this.getStudents(filter);
        var inputStream = filter.getInputStream();
        var title = filter.getReportTitle();
        var exportType = filter.getExportType();
        var dynamicReportProperties = studentInfoReportFiller.generateDynamicColumnAndRows(studentResponseList);
        var report = new DynamicReport(inputStream, title, exportType, response, dynamicReportProperties);
        dynamicReportService.export(report);
    }

    public void exportStudentGroupByReport(StudentFilter filter, HttpServletResponse response) throws IOException, JRException {
        var studentCountInformations = this.getStudentCountInfoByGroup(filter);
        var inputStream = filter.getInputStream();
        var title = filter.getReportTitle();
        var exportType = filter.getExportType();
        var dynamicReportProperties = studentCountInfoRepostFiller.generateDynamicColumnAndRows(studentCountInformations, filter.getGroupByList());
        var report = new DynamicReport(inputStream, title, exportType, response, dynamicReportProperties);
        dynamicReportService.export(report);
    }

    private List<StudentCountInformation> getStudentCountInfoByGroup(
        StudentFilter filter) {
        filter.getGroupByList().sort(Comparator.comparingInt(StudentGroupBy::getOrder));
        var listOfGroupBy = new HashSet<>(filter.getGroupByList());

        List<StudentCountInformation> studentResponseList = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);
        Root<StudentEntity> root = criteriaQuery.from(StudentEntity.class);
        List<Predicate> predicateList = new ArrayList<>();
        List<Selection<?>> rootList = new ArrayList<>();
        List<Expression<?>> groupByExpressionList = new ArrayList<>();
        List<Order> orderByList = new ArrayList<>();

        // dynamic group by
        for (var groupBy : listOfGroupBy) {
            applyGroupBy(root, cb, rootList, groupByExpressionList, orderByList, groupBy);
        }

        // apply filter
        if (StringUtils.isNotBlank(filter.getAdmissionYear()))
            predicateList.add(cb.equal(root.get("admissionYear"), filter.getAdmissionYear()));
        if (StringUtils.isNotBlank(filter.getCurrentSemester()))
            predicateList.add(cb.equal(root.get("currentSemester"), filter.getCurrentSemester()));
        if (StringUtils.isNotBlank(filter.getCurrentSection()))
            predicateList.add(cb.equal(root.get("currentSection"), filter.getCurrentSection()));
        if (StringUtils.isNotBlank(filter.getCurrentCgpa()))
            predicateList.add(cb.equal(root.get("currentCgpa"), filter.getCurrentCgpa()));

        // common
        rootList.add(cb.count(root));
        criteriaQuery.multiselect(rootList);
        criteriaQuery.groupBy(groupByExpressionList);
        criteriaQuery.orderBy(orderByList);
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);

        List<Object[]> resultList = query.getResultList();
        resultList.forEach(
            objects -> {
                var student = new StudentCountInformation();

                int index = 0;

                // dynamic
                for (var groupBy : listOfGroupBy) {
                    switch (groupBy) {
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
                    }
                    index++;
                }

                // static
                student.setCount(Integer.parseInt(objects[objects.length - 1].toString()));

                studentResponseList.add(student);
            });

        return studentResponseList;
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


            default:
                throw new GlobalCustomException("GroupBy parameter not found", HttpStatus.NOT_FOUND);
        }
    }
}
