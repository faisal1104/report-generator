package com.faisal_bs23.jasperreportusingjpaspecification.domain.report.dynamic;

import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.ExportType;
import com.faisal_bs23.jasperreportusingjpaspecification.domain.report.Report;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Collection;

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
