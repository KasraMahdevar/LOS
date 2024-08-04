package com.example.bsc.los.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class LosDataSourceConfig{

    @Bean(name="losDataSource")
    DataSource losDataSource(){
        var hikariDatasource = new HikariDataSource();
        hikariDatasource.setJdbcUrl("jdbc:jtds:sqlserver://los.bsc-intern.de/Schoko_GDS_Lager_Transfer");
        hikariDatasource.setUsername("Turki");
        hikariDatasource.setPassword("pMvGDL12wemx97D");
        hikariDatasource.setConnectionTestQuery("SELECT 1");
        System.out.println("Alle Konfigurationen wurden erfolgreich initialisiert...");
        return hikariDatasource;
    }


    @Bean(name = "losJDBCTemplate")
    JdbcTemplate losJDBCTemplate(@Qualifier("losDataSource") DataSource losDataSource){
        var jdbcTemplate = new JdbcTemplate(losDataSource);
        System.out.println("JDBCTemplate generiert und wird zurueckgegeben...");
        return jdbcTemplate;
    }

}