package com.faisal.reportgeneratorusingjasper.configuration.multitenancy;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
class TenantFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    String tenantName = req.getHeader("X-TenantID");
    TenantContext.setCurrentTenant(tenantName);

    try {
      chain.doFilter(request, response);
    } finally {
      TenantContext.setCurrentTenant("terpal");
    }
  }
}
