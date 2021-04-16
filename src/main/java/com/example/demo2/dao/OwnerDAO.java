package com.example.demo2.dao;

import com.example.demo2.entity.Owner;
import com.example.demo2.mapper.OwnerMapper;
import com.example.demo2.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerDAO {

    private static JdbcTemplate jdbcTemplate;

    private static EntityValidator entityValidator;

    @Autowired
    public OwnerDAO(JdbcTemplate jdbcTemplate, EntityValidator entityValidator) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityValidator = entityValidator;
    }

    public List<Owner> owners() {
        return jdbcTemplate.query("SELECT * FROM test.\"Owner\"", new OwnerMapper());
    }

    public void save(Owner owner) {
        jdbcTemplate.update("INSERT INTO test.\"Owner\" (name, age, email) VALUES (?,?,?)",
                owner.getName(), owner.getAge(), owner.getEmail());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM test.\"Owner\" WHERE id=?", id);
    }

    //show Owner by ID, realization without sql injection
    public Owner show(int id) {
        if (entityValidator.ownerExistById(id)){
            return jdbcTemplate.queryForObject("SELECT * FROM test.\"Owner\" WHERE id=?",
                    new Object[]{id}, new OwnerMapper());
        }
        return new Owner();

    }

    public void update(int id, Owner owner) {
        jdbcTemplate.update("UPDATE test.\"Owner\" SET name=?, age=?, email=? WHERE id=?",
                owner.getName(), owner.getAge(), owner.getEmail(), id);
    }
}