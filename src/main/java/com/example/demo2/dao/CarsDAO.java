package com.example.demo2.dao;

import com.example.demo2.entity.Car;
import com.example.demo2.mapper.CarMapper;
import com.example.demo2.validator.EntitiesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

// DAO bean for work with database
@Component
public class CarsDAO {
    // jdbc bean for connect with database
    private final JdbcTemplate jdbcTemplate;

    private final EntitiesValidator entitiesValidator;

    @Autowired
    public CarsDAO(JdbcTemplate jdbcTemplate, EntitiesValidator entitiesValidator) {
        this.jdbcTemplate = jdbcTemplate;
        this.entitiesValidator = entitiesValidator;
    }


    public List<Car> cars() {
        return jdbcTemplate.query("SELECT * FROM test.\"Car\"", new CarMapper());
    }

    public void save(Car car) {
        jdbcTemplate.update("INSERT INTO test.\"Car\" (company, model, owner_id, year) VALUES (?,?,?,?)",
                car.getCompany(), car.getModel(), car.getOwnerId(), car.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM test.\"Car\" WHERE id=?", id);
    }

    //show Car by ID, realization without sql injection
    public Car show(int id) {
        // here need to make page 404
        return jdbcTemplate.queryForObject("SELECT * FROM test.\"Car\" WHERE id=?",
                new Object[]{id}, new CarMapper());
    }

    // update function
    public void update(int id, Car car) {
        jdbcTemplate.update("UPDATE test.\"Car\" SET company=?, model=?, owner_id=?, year=? WHERE id=?",
                car.getCompany(), car.getModel(), car.getOwnerId(), car.getYear(), id);
    }
}
