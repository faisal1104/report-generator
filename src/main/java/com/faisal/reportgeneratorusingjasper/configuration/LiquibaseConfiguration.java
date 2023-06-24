package com.faisal.reportgeneratorusingjasper.configuration;

import com.faisal.reportgeneratorusingjasper.util.Constant;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

  @Bean
  public SpringLiquibase liquibaseForIIUC(DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setLiquibaseSchema(Constant.SCHEMA_NAME_IIUC);
    liquibase.setChangeLog("classpath:db-changelog/iiuc/db.changelog-root.xml");
    return liquibase;
  }

  @Bean
  public SpringLiquibase liquibaseForCUET(DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setLiquibaseSchema(Constant.SCHEMA_NAME_CUET);
    liquibase.setChangeLog("classpath:db-changelog/cuet/db.changelog-root.xml");
    return liquibase;
  }
}
