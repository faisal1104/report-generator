package com.faisal.reportgeneratorusingjasper.domain.report.dynamic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter@Setter
public class DynamicReportProperties {
  List<String> columnHeaders;
  List<Integer> indexesOfColumnTypeNumber;
  List<List<String>> rows;
  List<String> summary;
}
