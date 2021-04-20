package com.example.demo2.dao;

import com.example.demo2.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WeatherDao {

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public WeatherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Weather dto) {
        if (dto == null)
            return;
        String sql = "INSERT INTO test.\"Weather\" (city, country, created_date, updated_date, temp, rh, clouds) " +
                "VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                dto.getCity(), dto.getCountryCode(), new Date().getTime(), new Date().getTime(), dto.getTemp(), dto.getRh(), dto.getClouds());

        // make save logic for db
    }
}
