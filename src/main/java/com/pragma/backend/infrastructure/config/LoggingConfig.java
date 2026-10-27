package com.pragma.backend.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class LoggingConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    private static final String CORRELATION_ID_MDC_KEY = "correlationId";
    private static final String USER_ID_MDC_KEY = "userId";
    private static final String REQUEST_URI_KEY = "requestUri";
    private static final String HTTP_METHOD_KEY = "httpMethod";

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> loggingFilter() {
        FilterRegistrationBean<OncePerRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain filterChain) 
                    throws ServletException, IOException {
                String correlationId = getOrGenerateCorrelationId(request);
                MDC.put(CORRELATION_ID_MDC_KEY, correlationId);
                MDC.put(REQUEST_URI_KEY, request.getRequestURI());
                MDC.put(HTTP_METHOD_KEY, request.getMethod());

                long startTime = System.currentTimeMillis();
                logger.info("Peticion recibida - metodo: {}, uri: {}, correlationId: {}", 
                    request.getMethod(), request.getRequestURI(), correlationId);

                try {
                    filterChain.doFilter(request, response);
                } finally {
                    long duration = System.currentTimeMillis() - startTime;
                    logger.info("Peticion completada - metodo: {}, uri: {}, status: {}, duracion: {}ms, correlationId: {}", 
                        request.getMethod(), 
                        request.getRequestURI(), 
                        response.getStatus(), 
                        duration, 
                        correlationId);
                    MDC.clear();
                }
            }

            private String getOrGenerateCorrelationId(HttpServletRequest request) {
                String correlationId = request.getHeader(CORRELATION_ID_HEADER);
                if (correlationId == null || correlationId.isBlank()) {
                    correlationId = UUID.randomUUID().toString();
                }
                return correlationId;
            }
        });

        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setName("loggingFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> mdcFilter() {
        FilterRegistrationBean<OncePerRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain filterChain) 
                    throws ServletException, IOException {
                String correlationId = request.getHeader(CORRELATION_ID_HEADER);
                if (correlationId == null || correlationId.isBlank()) {
                    correlationId = UUID.randomUUID().toString();
                }
                response.setHeader(CORRELATION_ID_HEADER, correlationId);
                filterChain.doFilter(request, response);
            }
        });

        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("mdcFilter");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}