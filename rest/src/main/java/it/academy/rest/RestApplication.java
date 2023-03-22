package it.academy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"it.academy.repository","it.academy.rest.controller", "it.academy.configuration", "it.academy.service"})
@EntityScan("it.academy.model")
public class RestApplication {

    public static void main(String[] args) {
        System.setProperty("liquibase.secureParsing", System.getProperty("liquibase.secureParsing", "false"));
        SpringApplication.run(RestApplication.class, args);
    }

}
