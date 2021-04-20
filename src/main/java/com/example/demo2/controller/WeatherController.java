package com.example.demo2.controller;

import com.example.demo2.entity.Weather;
import com.example.demo2.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/{country}&{city}")
    public String showWeather(@PathVariable("country") String country, @PathVariable("city") String city, Model model) {
        Weather weather = service.initialParametersAndRun(country, city);
        model.addAttribute("weather", weather);
        return "weather/weather";
    }

}
