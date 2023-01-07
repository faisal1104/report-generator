package com.faisal.reportgeneratorusingjasper.domain;

import com.faisal.reportgeneratorusingjasper.domain.report.ExportType;
import com.faisal.reportgeneratorusingjasper.util.Constant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.InputStream;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class StudentFilter {
    private String admissionYear;
    private String currentSemester;
    private String currentSection;
    private String currentCgpa;
    private Integer creditCompleted;
    private ExportType exportType;
    private List<StudentGroupBy> groupByList;


    public InputStream getInputStream() {
        var name =
            Constant.FOLDER_PATH_REPORT_DYNAMIC
                + exportType.toString().toLowerCase()
                + Constant.EXTENSION_JRXML;
        return getClass().getResourceAsStream(name);
    }

    public String getReportTitle() {
        return groupByList != null ? "Student Group By List" : "Student List";
    }
}
