package com.example.bsc.los.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class LosDataSourceConfig{

    @Bean(name="losDataSource")
    @ConfigurationProperties(prefix = "app.los.datasource")
    DataSource losDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "losJDBCTemplate")
    JdbcTemplate losJDBCTemplate(@Qualifier("losDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }







}