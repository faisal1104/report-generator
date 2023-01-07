package com.faisal.reportgeneratorusingjasper.controller;

import com.faisal.reportgeneratorusingjasper.domain.StudentGroupBy;
import com.faisal.reportgeneratorusingjasper.domain.StudentResponse;
import com.faisal.reportgeneratorusingjasper.domain.report.ExportType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(value = "student-report", tags = "student-report")
@RequestMapping(path = "/student")
public interface StudentReportApi {

    @ApiOperation(
        value = "Get All Student List",
        nickname = "getAllStudentList",
        notes = "Get All Student List as Json Data",
        tags = {"student-report"}, response = StudentResponse.class)
    @GetMapping
    ResponseEntity<List<StudentResponse>> getAllStudentList(
        @RequestParam(value = "year", required = false) final String year,
        @RequestParam(value = "semester", required = false) final String semester,
        @RequestParam(value = "section", required = false) final String section,
        @RequestParam(value = "creditCompleted", required = false) final Integer creditCompleted);

    @ApiOperation(
        value = "Export All Student List",
        nickname = "exportAllStudentReport",
        notes = "Export (PDF,DOCX,EXCEL) Report of All Student",
        tags = {"student-report"}, response = StudentResponse.class)
    @GetMapping("export")
    ResponseEntity<Void> exportAllStudentReport(
        @RequestParam(value = "year", required = false) final String year,
        @RequestParam(value = "semester", required = false) final String semester,
        @RequestParam(value = "section", required = false) final String section,
        @RequestParam(value = "creditCompleted", required = false) final Integer creditCompleted,
        @RequestParam(value = "exportType") final ExportType exportType,
        HttpServletResponse response) throws IOException, JRException;

    @ApiOperation(
        value = "Export Student Report by Group",
        nickname = "exportStudentGroupByReport",
        notes = "Export (PDF,DOCX,EXCEL,CSV) Student Report by Group",
        tags = {"student-report"}, response = StudentResponse.class)
    @GetMapping("groupby-report/export")
    ResponseEntity<Void> exportStudentGroupByReport(
        @RequestParam(value = "groupBy", required = false) final List<StudentGroupBy> groupBy,
        @RequestParam(value = "year", required = false) final String year,
        @RequestParam(value = "semester", required = false) final String semester,
        @RequestParam(value = "section", required = false) final String section,
        @RequestParam(value = "exportType") final ExportType exportType,
        HttpServletResponse response) throws IOException, JRException;

}