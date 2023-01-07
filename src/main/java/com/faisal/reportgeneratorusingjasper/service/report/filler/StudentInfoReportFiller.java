package com.faisal.reportgeneratorusingjasper.service.report.filler;

import com.faisal.reportgeneratorusingjasper.domain.StudentResponse;
import com.faisal.reportgeneratorusingjasper.domain.report.dynamic.DynamicReportProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class StudentInfoReportFiller {
    public DynamicReportProperties generateDynamicColumnAndRows(List<StudentResponse> list) {

        List<String> columnHeaders = new ArrayList<>();
        List<Integer> indexesOfColumnTypeNumber = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();
        List<String> summary = new ArrayList<>();
        AtomicInteger studentCounter = new AtomicInteger(0);

        columnHeaders.add("Student Id");
        columnHeaders.add("Student Name");
        columnHeaders.add("Admission Year");
        columnHeaders.add("Current Semester");
        columnHeaders.add("Current Section");
        columnHeaders.add("Current CGPA");
        columnHeaders.add("Credit Completed");


        for (var student : list) {
            List<String> row = new ArrayList<>();

            row.add(student.getStudentId());
            row.add(student.getStudentName());
            row.add(student.getAdmissionYear());
            row.add(student.getCurrentSemester());
            row.add(student.getCurrentSection());
            row.add(student.getCurrentCgpa());
            row.add(student.getCreditCompleted().toString());

            rows.add(row);
        }
        // summary
        summary.add("Total Student");
        summary.add(String.valueOf(list.size()));

        // number field index list
        indexesOfColumnTypeNumber.add(columnHeaders.size() - 2); // "Total"
        indexesOfColumnTypeNumber.add(columnHeaders.size() - 1); // studentNumber

        return new DynamicReportProperties(columnHeaders, indexesOfColumnTypeNumber, rows, summary);
    }
}
