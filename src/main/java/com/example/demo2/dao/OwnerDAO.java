package com.example.demo2.dao;

import com.example.demo2.Demo2Application;
import com.example.demo2.entity.Owner;
import com.example.demo2.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.function.ServerResponse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class OwnerDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OwnerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Owner> owners(Model model) {
        List<Owner> owners = jdbcTemplate.query("SELECT * FROM test.\"Owner\"", new OwnerMapper());
        return owners;
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
        Owner owner;
        owner = jdbcTemplate.queryForObject("SELECT * FROM test.\"Owner\" WHERE id=?",
                new Object[]{id}, new OwnerMapper());
        return owner;
    }

    public void update(int id, Owner owner) {
        jdbcTemplate.update("UPDATE test.\"Owner\" SET name=?, age=?, email=? WHERE id=?",
                owner.getName(), owner.getAge(), owner.getEmail(), id);
    }
}

// КОД ДЛЯ POST запросов, которые ничего не возвращают
//jdbcTemplate.execute("SELECT * FROM test.\"Owner\" WHERE id=?", new PreparedStatementCallback<Boolean>() {
//@Override
//public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException,
//        DataAccessException {
//        preparedStatement.setInt(1, id);
//        return preparedStatement.execute();
//        }
//        });