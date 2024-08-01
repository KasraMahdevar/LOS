package com.example.bsc.los.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class LosService {
    
       @Autowired
        private JdbcTemplate jdbcTemplate;

    public List<String> getTableNames() {
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG='Schoko_GDS_Lager_Transfer' "; 
        return jdbcTemplate.queryForList(sql, String.class);
    }

}
