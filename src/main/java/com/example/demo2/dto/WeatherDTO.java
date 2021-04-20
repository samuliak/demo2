package com.example.demo2.dto;

import com.example.demo2.entity.Weather;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {

    @JsonProperty("city_name")
    private String city;

    @JsonProperty("country_code")
    private String countryCode;

    private double temp;

    private double rh;

    private double clouds;

    public Weather toWeather() {
        Weather weather = new Weather();
        weather.setCity(city);
        weather.setCountryCode(countryCode);
        weather.setTemp(temp);
        weather.setRh(rh);
        weather.setClouds(clouds);
        weather.setCreatedDate(new Date().getTime());
        weather.setUpdateDate(new Date().getTime());
        weather.setCreatedDateInDateStyle(new Date(weather.getCreatedDate()));
        weather.setUpdatedDateInDateStyle(new Date(weather.getUpdateDate()));

        return weather;
    }

    public static WeatherDTO fromWeather(Weather weather) {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setCity(weather.getCity());
        weatherDTO.setCountryCode(weather.getCountryCode());
        weatherDTO.setTemp(weather.getTemp());
        weatherDTO.setRh(weather.getRh());
        weatherDTO.setClouds(weather.getClouds());

        return weatherDTO;
    }

    public WeatherDTO() {
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
}
