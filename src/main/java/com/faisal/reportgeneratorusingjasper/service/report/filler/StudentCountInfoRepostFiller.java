package com.faisal.reportgeneratorusingjasper.service.report.filler;

import com.faisal.reportgeneratorusingjasper.domain.StudentCountInformation;
import com.faisal.reportgeneratorusingjasper.domain.StudentGroupBy;
import com.faisal.reportgeneratorusingjasper.domain.report.dynamic.DynamicReportProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentCountInfoRepostFiller {
    public DynamicReportProperties generateDynamicColumnAndRows(List<StudentCountInformation> studentCountInformations, List<StudentGroupBy> groupByList) {
        List<String> columnHeaders = new ArrayList<>();
        List<Integer> indexesOfColumnTypeNumber = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();
        List<String> summary = new ArrayList<>();

        int studentCounter = 0;

        //dynamically add header
        groupByList.sort(Comparator.comparingInt(StudentGroupBy::getOrder));
        for (var groupBy : groupByList) {
            columnHeaders.add(groupBy.getDescription());
        }
        //static header
        columnHeaders.add("Student Count");

        for (var student : studentCountInformations) {
            List<String> row = new ArrayList<>();

            if (groupByList.contains(StudentGroupBy.ADMISSION_YEAR))
                row.add(student.getAdmissionYear());
            if (groupByList.contains(StudentGroupBy.SEMESTER))
                row.add(student.getCurrentSemester());
            if (groupByList.contains(StudentGroupBy.SECTION))
                row.add(student.getCurrentSection());

            studentCounter+=student.getCount();
            row.add(student.getCount().toString());
            rows.add(row);
        }

        // summary
        summary.add("Total Student");
        summary.add(String.valueOf(String.valueOf(studentCounter)));

        // number field index list
        indexesOfColumnTypeNumber.add(columnHeaders.size() - 2); // "Total"
        indexesOfColumnTypeNumber.add(columnHeaders.size() - 1); // studentNumber

        return new DynamicReportProperties(columnHeaders, indexesOfColumnTypeNumber, rows, summary);
    }
}
