package com.example.demo2.entity;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class Owner {
    private int id;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30")
    private String name;

    @Min(value = 18, message = "Age should be between 18 and 99")
    @Max(value = 99, message = "Age should be between 18 and 99")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    private List<Car> carList = new ArrayList<>();

    public Owner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

}
