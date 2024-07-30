package com.example.bsc.prozesse.dataSource;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager",
        basePackages = {
                "com.example.bsc.prozesse.waren.repo",
                "com.example.bsc.prozesse.stapler.repo",
                "com.example.bsc.prozesse.lagerplatz.repo",
                "com.example.bsc.prozesse.eingangsetikett.repo",
                "com.example.bsc.prozesse.bestellung.repo",
                "com.example.bsc.prozesse.besteller.repo"
        }
)
public class PostgreSQLDataConfiguration {


    @Bean(name = "postgresDataSource")
    @Primary
    @ConfigurationProperties(prefix = "app.post.datasource")
    DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresEntityManagerFactory")
    @PersistenceContext
    LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("postgresDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.bsc.prozesse.entities")
                .persistenceUnit("postgresDataSource")
                .build();



    }

    @Bean(name = "postgresTransactionManager")
    PlatformTransactionManager platformTransactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
