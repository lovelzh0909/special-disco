package com.example.demo.dao.impl;

import com.example.demo.dao.userLogonDao;
import com.example.demo.entity.Userlogon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userlogon")
public class userLogonDaoImpl implements userLogonDao{
    private final JdbcTemplate jdb;
    
    @Autowired
    public userLogonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdb = jdbcTemplate;
    }
    @Override
    public Userlogon selectById(String Id) {
        // TODO Auto-generated method stub
        String sql = "select * from userlogon where Id = ?";
        try {
            return jdb.queryForObject(sql, Userlogon.class,Id);
        } catch (DataAccessException e) {
            
        }
        return null;
    }
}
