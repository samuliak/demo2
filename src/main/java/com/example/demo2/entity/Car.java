package com.example.demo2.entity;


public class Car {

    private int id;
    private String company;
    private String model;
    private int owner_id;

    public Car() {
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
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

    public int getOwner_id() {
        return owner_id;
    }
}
