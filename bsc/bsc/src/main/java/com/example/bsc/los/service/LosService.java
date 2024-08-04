package com.example.bsc.los.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class LosService {

    @Autowired
    @Qualifier("losJDBCTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<String> getTableNames() {
        String sql = "SELECT name FROM  Schoko_GDS_Lager_Transfer.dbo.Mandant";
        return jdbcTemplate.queryForList(sql, String.class);
    }

}
