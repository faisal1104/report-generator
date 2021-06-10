package com.faisal.reportgeneratorusingjasper.persistence.repository;

import com.faisal.reportgeneratorusingjasper.persistence.entity.JasperDesignConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JasperDesignConfigurationRepository extends JpaRepository<JasperDesignConfigurationEntity, Long> {
    Optional<JasperDesignConfigurationEntity> findByReportOwnerName(String reportOwnerName);
}
