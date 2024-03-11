package me.shijunjiee.camundastudy.config;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaAuthFilterConfig implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic camundaAuthFilter = servletContext.addFilter("camundaAuthFilter", ProcessEngineAuthenticationFilter.class);
        camundaAuthFilter.setAsyncSupported(true);
        camundaAuthFilter.setInitParameter("authentication-provider", "org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
        camundaAuthFilter.addMappingForUrlPatterns(null, true, "/engine-rest/*");
    }
}
