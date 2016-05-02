package edu.sjsu.controller;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
//@PropertySource(value = {"classpath:application.properties"})
public class CassandraSessionManager {

  /*  @Autowired
    private Environment env;*/

    private Session session;
    private Cluster cluster;

    private String contactPoint="127.0.0.1";

    private String keyspace="java_api";

    public CassandraSessionManager() {

    }

    public Session getSession() {
        return session;
    }

    @PostConstruct
    public void initIt() {
        cluster = Cluster.builder().addContactPoint(
                contactPoint).build();
        session = cluster.connect(keyspace);
    }

    @PreDestroy
    public void destroy() {
        if (session != null) {
            session.close();
        }
        if (cluster != null) {
            cluster.close();
        }
    }
}