package com.faisal.reportgeneratorusingjasper.domain.report;

import com.faisal.reportgeneratorusingjasper.exceptionhandler.GlobalCustomException;
import com.faisal.reportgeneratorusingjasper.util.Constant;
import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;


@Data
public class Report {
    InputStream inputStream;
    String title;
    Collection<?> beanCollection;
    ExportType exportType;
    HttpServletResponse response;

    public Report(
        InputStream inputStream,
        String title,
        ExportType exportType,
        HttpServletResponse response) {
        this.inputStream = inputStream;
        this.title = title;
        this.exportType = exportType;
        this.response = response;
    }

    public void exportViaJasperReport(JasperPrint jasperPrint) throws JRException, IOException {
        if (exportType == ExportType.PDF) {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            setHeaderAndContentType(response, exportType);
            exporter.exportReport();
        } else if (exportType == ExportType.EXCEL) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
            reportConfigXLS.setSheetNames(new String[]{title});
            reportConfigXLS.setDetectCellType(true);
            reportConfigXLS.setCollapseRowSpan(false);
            reportConfigXLS.setRemoveEmptySpaceBetweenRows(true);
            reportConfigXLS.setRemoveEmptySpaceBetweenColumns(true);
            exporter.setConfiguration(reportConfigXLS);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            setHeaderAndContentType(response, exportType);
            exporter.exportReport();
        } else if (exportType == ExportType.DOCX) {
            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            setHeaderAndContentType(response, exportType);
            exporter.exportReport();
        }
/*    else if (exportType == ExportType.CSV) {
      JRCsvExporter exporter = new JRCsvExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      var outputStream = response.getOutputStream();
      exporter.setExporterOutput((new SimpleWriterExporterOutput(outputStream)));
      setHeaderAndContentType(response, exportType);
      exporter.exportReport();
    }*/
        else {
            throw new GlobalCustomException("File Format isn't supported!", HttpStatus.FORBIDDEN);
        }
    }

    private void setHeaderAndContentType(HttpServletResponse response, ExportType exportType)
        throws UnsupportedEncodingException {
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, getHeaderValue(exportType));
        response.setContentType(exportType.getContentType());
    }

    private String getHeaderValue(ExportType exportType) throws UnsupportedEncodingException {
        var formatter = DateTimeFormatter.ofPattern("MMMdd-yyyy-HHmm");
        var dateTimeNow = LocalDateTime.now().format(formatter);
        var fileName =
            URLEncoder.encode(title.replaceAll("[^\\w-]+", "_") + "_" + dateTimeNow, "UTF-8");
        return Constant.ATTACHMENT_FILENAME + fileName + exportType.getExtension() + ";";
    }
}
