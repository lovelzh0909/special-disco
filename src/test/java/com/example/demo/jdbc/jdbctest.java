package com.example.demo.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@SpringBootTest
@Slf4j

public class jdbctest {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    public void contextLoads() {

////        jdbcTemplate.queryForObject("select * from account_tbl")
////        jdbcTemplate.queryForList("select * from account_tbl",)
//        Long aLong = jdbcTemplate.queryForObject("select count(*) from t_user", Long.class);
//        log.info("记录总数：{}",aLong);
    }
}
