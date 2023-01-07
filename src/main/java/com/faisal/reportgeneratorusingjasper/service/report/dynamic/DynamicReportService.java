package com.faisal.reportgeneratorusingjasper.service.report.dynamic;

import com.faisal.reportgeneratorusingjasper.domain.report.ExportType;
import com.faisal.reportgeneratorusingjasper.domain.report.dynamic.DynamicReport;
import com.faisal.reportgeneratorusingjasper.service.report.JasperReportDesignConfigurationService;
import com.faisal.reportgeneratorusingjasper.util.Constant;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class DynamicReportService {

    @Value("${report.owner.name}")
    private String reportOwnerName;

    private final JasperReportDesignConfigurationService jasperReportDesignConfigurationService;

    public void export(DynamicReport dynamicReport) throws JRException, IOException {
        if (dynamicReport.getExportType() == null)
            throw new RuntimeException(Constant.EXPORT_TYPE_PARAMETER_IS_MISSING);

        var jasperDynamicReport = dynamicReport.getDynamicReportProperties();
        var jasperReportDesign = JRXmlLoader.load(dynamicReport.getInputStream());

        var jasperDesignConfig = jasperReportDesignConfigurationService.getConfigurationByClientName(reportOwnerName);

        var reportBuilder =
            new DynamicReportBuilder(jasperReportDesign, jasperDynamicReport.getColumnHeaders().size(), jasperDesignConfig.getThemeColorCode());
        reportBuilder.addDynamicColumns(jasperDynamicReport.getIndexesOfColumnTypeNumber());
        var dataSource =
            new DynamicColumnDataSource(
                jasperDynamicReport.getColumnHeaders(),
                jasperDynamicReport.getRows(),
                jasperDynamicReport.getSummary());

        HashMap<String, Object> params = new HashMap();
        params.put("title", dynamicReport.getTitle());
        params.put("themeColor", jasperDesignConfig.getThemeColorCode());
        // This param ignore pagination for excel & csv to prevent duplicate ColumnHeader
        if (dynamicReport.getExportType() == ExportType.EXCEL)
            params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

        var jasperReport = JasperCompileManager.compileReport(jasperReportDesign);
        var jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        dynamicReport.exportViaJasperReport(jasperPrint);
    }
}
