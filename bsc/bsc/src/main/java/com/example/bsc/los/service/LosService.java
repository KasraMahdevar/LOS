package com.example.bsc.los.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LosService {

    @Autowired
    @Qualifier("losJDBCTemplate")
    private JdbcTemplate jdbcTemplate;

    private final String DB_NAME = "Schoko_GDS_Lager_Transfer";

    public List<String> getTableNames() {
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG=" + DB_NAME;
        return jdbcTemplate.queryForList(sql, String.class);
    }

}
