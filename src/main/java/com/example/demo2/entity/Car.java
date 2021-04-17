package com.example.demo2.entity;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Car {

    private int id;

    @Size(min = 3, max = 30, message = "Company should be between 2 and 30")
    private String company;

    @Size(min = 1, max = 30, message = "Company should be between 1 and 20")
    private String model;

    private int ownerId;

    @Min(value = 1965, message = "Year should be between 1965 and 2022")
    @Max(value = 2022, message = "Year should be between 1965 and 2022")
    private int year;

    public Car() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
