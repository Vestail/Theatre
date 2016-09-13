package com.vitaliivitrenko.theatre.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * Created by Vitalii_Vitrenko on 9/13/2016.
 */
@Configuration
public class RepositoryConfig {

    public static final String JNDI_NAME = "jdbc/dataSource";

    @Bean
    public JndiObjectFactoryBean jndiObjectFactoryBean() {
        JndiObjectFactoryBean factory = new JndiObjectFactoryBean();
        factory.setJndiName(JNDI_NAME);
        factory.setResourceRef(true);
        factory.setProxyInterface(DataSource.class);
        return factory;
    }



    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setLocations("db-migration");
        flyway.setDataSource(dataSource);
        flyway.setOutOfOrder(true);
        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    public JdbcOperations jdbcOperations(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
