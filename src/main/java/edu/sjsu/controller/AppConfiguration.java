package edu.sjsu.controller;

import com.datastax.driver.core.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Naks on 01-May-16.
 * Bean configuration file
 */

@Configuration
public class AppConfiguration {

    @Bean
    public Session session() {
        return sessionManager().getSession();
    }

    @Bean
    public CassandraSessionManager sessionManager() {
        return new CassandraSessionManager();
    }
}
