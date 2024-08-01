package com.example.bsc.los.datasource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef="losEntityManagerFactory",
    transactionManagerRef="losTransactionManager",
    basePackages={
        "com.example.bsc.los.repo"
    } 
)
public class LosDataSourceConfig{

    @Bean(name="losDataSource")
    @ConfigurationProperties(prefix = "app.los.datasource")
    DataSource losDataSource(){
        System.out.println("vesucht eine Verbindung zu erstellen");
        return DataSourceBuilder.create().build();
    }


    @Bean(name="losEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean lostEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("losDataSource") DataSource dataSource){
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.example.bsc.prozesse.entities")
                .properties(properties)
                .persistenceUnit("losDataSource")
                .build();
    }

    @Bean(name="losTransactionManager")
    PlatformTransactionManager losTransactionManager(@Qualifier("losEntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    } 


}