package com.example.demo2.dao;

import com.example.demo2.entity.Owner;
import com.example.demo2.mapper.CarMapper;
import com.example.demo2.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerDAO {

    private static JdbcTemplate jdbcTemplate;


    @Autowired
    public OwnerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        Owner owner = jdbcTemplate.queryForObject("SELECT * FROM test.\"Owner\" WHERE id=?",
                new Object[]{id},
                new OwnerMapper());
        owner.setCarList(jdbcTemplate.query("SELECT * FROM test.\"Car\" WHERE owner_id=?",
                new Object[]{id},
                new CarMapper()));
        return owner;
    }

    public void update(int id, Owner owner) {
        jdbcTemplate.update("UPDATE test.\"Owner\" SET name=?, age=?, email=? WHERE id=?",
                owner.getName(), owner.getAge(), owner.getEmail(), id);
    }
}