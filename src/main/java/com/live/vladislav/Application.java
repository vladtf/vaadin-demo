package com.live.vladislav;

import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.osgi.VaadinBundleTracker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
