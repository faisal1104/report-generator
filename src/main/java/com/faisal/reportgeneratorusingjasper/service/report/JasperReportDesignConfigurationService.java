package com.faisal.reportgeneratorusingjasper.service.report;

import com.faisal.reportgeneratorusingjasper.domain.report.JasperDesignConfiguration;
import com.faisal.reportgeneratorusingjasper.persistence.entity.JasperDesignConfigurationEntity;
import com.faisal.reportgeneratorusingjasper.persistence.repository.JasperDesignConfigurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JasperReportDesignConfigurationService {
  private final JasperDesignConfigurationRepository reportConfigurationRepository;

  private static final String greenColorCode = "#00A95D";

  public void add(JasperDesignConfiguration reportConfiguration) {
    var entity =
        reportConfigurationRepository
            .findByReportOwnerName(reportConfiguration.getReportOwnerName())
            .orElseGet(JasperDesignConfigurationEntity::new);
    BeanUtils.copyProperties(reportConfiguration, entity, "id");
    reportConfigurationRepository.save(entity);
  }

  public List<JasperDesignConfiguration> getAll() {
    return reportConfigurationRepository.findAll().stream()
        .map(
            e -> {
              var domain = new JasperDesignConfiguration();
              BeanUtils.copyProperties(e, domain);
              return domain;
            })
        .collect(Collectors.toList());
  }


  public JasperDesignConfiguration getConfigurationByClientName(String reportOwnerName) {
    return reportConfigurationRepository
        .findByReportOwnerName(reportOwnerName)
        .map(
            e -> {
              var domain = new JasperDesignConfiguration();
              BeanUtils.copyProperties(e, domain);
              return domain;
            })
        .orElse(new JasperDesignConfiguration().setThemeColorCode(greenColorCode));
  }
}