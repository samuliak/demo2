package com.example.demo2.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EntitiesValidator {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EntitiesValidator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean ownerExistById(int id) {
        int count = jdbcTemplate.queryForObject("SELECT count(*) FROM test.\"Owner\" WHERE id=?",
                new Object[]{id}, Integer.class);
        return count > 0;
    }

    public boolean carExistById(int id) {
        int count = jdbcTemplate.queryForObject("SELECT count(*) FROM test.\"Car\" WHERE id=?",
                new Object[]{id}, Integer.class);
        return count > 0;
    }
}
