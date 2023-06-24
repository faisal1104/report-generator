package com.faisal.reportgeneratorusingjasper.configuration.multitenancy;

import com.faisal.reportgeneratorusingjasper.util.Constant;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component("currentTenantIdentifierResolver")
public class CurrentTenantResolver implements CurrentTenantIdentifierResolver {

  @Override
  public String resolveCurrentTenantIdentifier() {
    // Retrieve the current tenant identifier from the TenantContext class
    String tenantIdentifier = TenantContext.getCurrentTenant();
    if (tenantIdentifier != null) {
      return tenantIdentifier;
    }
    return Constant.POSTGRES_SQL_DEFAULT_SCHEMA_NAME;
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
