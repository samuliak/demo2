package com.example.demo2.mapper;

import com.example.demo2.entity.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// class for correct data mapping between database and Car entity
public class CarMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int i) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setCompany(rs.getString("company"));
        car.setModel(rs.getString("model"));
        car.setOwnerId(rs.getInt("owner_id"));
        car.setYear(rs.getInt("year"));
        return car;
    }
}
