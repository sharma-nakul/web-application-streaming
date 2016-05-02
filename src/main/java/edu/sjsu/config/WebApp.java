package edu.sjsu.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Naks on 01-May-16.
 * Starting point of application
 */


@Configuration
@ComponentScan(basePackages = "edu.sjsu.controller")
@EnableAutoConfiguration
public class WebApp {
    public static void main(String[] args) {
        SpringApplication.run(
                WebApp.class, args);
    }
}
