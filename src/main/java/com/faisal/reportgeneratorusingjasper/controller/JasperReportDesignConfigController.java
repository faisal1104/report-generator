package com.faisal.reportgeneratorusingjasper.controller;

import com.faisal.reportgeneratorusingjasper.domain.report.JasperDesignConfiguration;
import com.faisal.reportgeneratorusingjasper.service.report.JasperReportDesignConfigurationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "report-design-config", tags = "report-design-config")
@RequestMapping(path = "/report-design-config")
@RestController
@RequiredArgsConstructor
public class JasperReportDesignConfigController {

    private final JasperReportDesignConfigurationService reportDesignConfigurationService;

    @ApiOperation(
        value = "Get all report design configuration",
        nickname = "getAll",
        notes = "Get all report design configuration",
        tags = {
            "report-design-config",
        })
    @GetMapping
    public ResponseEntity<List<JasperDesignConfiguration>> getAll() {
        return ResponseEntity.ok(reportDesignConfigurationService.getAll());
    }


    @ApiOperation(
        value = "Add report owner specific report design configuration",
        nickname = "addReportDesignConfiguration",
        notes = "Add report owner specific report design configuration",
        tags = {
            "report-design-config",
        })
    @PostMapping()
    public ResponseEntity<Void> addReportDesignConfiguration(@RequestBody JasperDesignConfiguration reportConfiguration) {
        reportDesignConfigurationService.add(reportConfiguration);
        return ResponseEntity.ok().build();
    }
}
