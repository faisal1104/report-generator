package com.faisal.reportgeneratorusingjasper.domain.report;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class JasperDesignConfiguration {

    private long id;

    private String reportOwnerName;

    private String themeColorCode;
}