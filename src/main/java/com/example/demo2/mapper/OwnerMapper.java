package com.example.demo2.mapper;

import com.example.demo2.entity.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class OwnerMapper implements RowMapper<Owner> {
    @Override
    public Owner mapRow(ResultSet rs, int i) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        owner.setAge(rs.getInt("age"));
        owner.setEmail(rs.getString("email"));
        return owner;
    }
}
