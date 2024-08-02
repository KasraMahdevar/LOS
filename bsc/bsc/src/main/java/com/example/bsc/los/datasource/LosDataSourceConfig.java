package com.example.bsc.los.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class LosDataSourceConfig{

    @Autowired
    private Environment environment;

    @Bean(name="losDataSource")
    @ConfigurationProperties(prefix = "app.los.datasource")
    DataSource losDataSource(){
        var hikariDatasource = new HikariDataSource();
        hikariDatasource.setJdbcUrl(environment.getProperty("app.los.datasource.jdbcUrl"));
        hikariDatasource.setUsername(environment.getProperty("app.los.datasource.username"));
        hikariDatasource.setPassword(environment.getProperty("app.los.datasource.password"));
        return hikariDatasource;
    }


    @Bean(name = "losJDBCTemplate")
    JdbcTemplate losJDBCTemplate(@Qualifier("losDataSource") DataSource dataSource){
        var jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}