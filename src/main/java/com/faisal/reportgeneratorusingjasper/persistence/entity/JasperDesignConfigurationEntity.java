package com.faisal.reportgeneratorusingjasper.persistence.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "jasper_design_configuration")
public class JasperDesignConfigurationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 50, unique = true)
  private String reportOwnerName;

  @Column(length = 10)
  private String themeColorCode;
}