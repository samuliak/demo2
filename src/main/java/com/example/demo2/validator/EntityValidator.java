package com.example.demo2.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EntityValidator {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EntityValidator(JdbcTemplate jdbcTemplate) {
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
