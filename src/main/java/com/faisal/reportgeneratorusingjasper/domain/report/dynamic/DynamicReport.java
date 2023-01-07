package com.faisal.reportgeneratorusingjasper.domain.report.dynamic;

import com.faisal.reportgeneratorusingjasper.domain.report.ExportType;
import com.faisal.reportgeneratorusingjasper.domain.report.Report;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Getter
@Accessors(chain = true)
public class DynamicReport extends Report {
    private final DynamicReportProperties dynamicReportProperties;

    public DynamicReport(
        InputStream inputStream,
        String title,
        ExportType exportType,
        HttpServletResponse response,
        DynamicReportProperties dynamicReportProperties) {
        super(inputStream, title, exportType, response);
        this.dynamicReportProperties = dynamicReportProperties;
    }
}
