package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class Weather {

    private int id;

    @Size(min = 2, max = 40)
    @JsonProperty("city_name")
    private String city;

    @Size(min = 2, max = 120)
    @JsonProperty("country_code")
    private String countryCode;

    @NotEmpty
    private Long createdDate;

    @NotEmpty
    private Long updateDate;

    private Date createdDateInDateStyle;

    private Date updatedDateInDateStyle;


    @Min(1)
    @Max(value = 3)
    private double temp;

    @Min(1)
    @Max(value = 3)
    private double rh;

    @Min(1)
    @Max(value = 3)
    private double clouds;

    public Weather() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getRh() {
        return rh;
    }

    public void setRh(double rh) {
        this.rh = rh;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }


    public Date getCreatedDateInDateStyle() {
        return createdDateInDateStyle;
    }

    public void setCreatedDateInDateStyle(Date createdDateInDateStyle) {
        this.createdDateInDateStyle = createdDateInDateStyle;
    }

    public Date getUpdatedDateInDateStyle() {
        return updatedDateInDateStyle;
    }

    public void setUpdatedDateInDateStyle(Date updatedDateInDateStyle) {
        this.updatedDateInDateStyle = updatedDateInDateStyle;
    }

    public static Weather getClone(Weather weather) {
        Weather newWeather = new Weather();
        newWeather.setId(weather.getId());
        newWeather.setCity(weather.getCity());
        newWeather.setCountryCode(weather.getCountryCode());
        newWeather.setCreatedDate(weather.getCreatedDate());
        newWeather.setUpdateDate(weather.getUpdateDate());
        newWeather.setTemp(weather.getTemp());
        newWeather.setRh(weather.getRh());
        newWeather.setClouds(weather.getClouds());

        return newWeather;
    }
}
