package com.home.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Ahmad Alrefai
 */
@Component
public class CustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory container) {
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.xhtml"));
        container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.xhtml"));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.xhtml"));
        container.addErrorPages(new ErrorPage("/error/error.xhtml"));
    }
}