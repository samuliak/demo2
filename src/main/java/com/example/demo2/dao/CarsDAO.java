package com.example.demo2.dao;

import com.example.demo2.entity.Car;
import com.example.demo2.mapper.CarMapper;
import com.example.demo2.validator.EntitiesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

// DAO bean for work with database
@Component
public class CarsDAO {
    // jdbc bean for connect with database
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final EntitiesValidator entitiesValidator;

    @Autowired
    public CarsDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                   EntitiesValidator entitiesValidator) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM test.\"Car\" WHERE id=:id",
                params, (rs, i) -> {
                    Car car = new Car();
                    car.setId(rs.getInt("id"));
                    car.setCompany(rs.getString("company"));
                    car.setModel(rs.getString("model"));
                    car.setOwnerId(rs.getInt("owner_id"));
                    car.setYear(rs.getInt("year"));
                    return car;
                });
    }

    // update function
    public void update(int id, Car car) {
        jdbcTemplate.update("UPDATE test.\"Car\" SET company=?, model=?, owner_id=?, year=? WHERE id=?",
                car.getCompany(), car.getModel(), car.getOwnerId(), car.getYear(), id);
    }
}
