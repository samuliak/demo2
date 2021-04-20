package com.example.demo2.service;

import com.example.demo2.dao.WeatherDao;
import com.example.demo2.dto.WeatherGeneralDTO;
import com.example.demo2.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@EnableScheduling
public class WeatherService {

    private WeatherDao weatherDao;

    private String city;
    private String country;


    private final RestTemplate template;

    private final String KEY_WEATHER = "c1f5ebb5d2b044dfbe4994f7d4f7594a";
    private final String HOST = "https://api.weatherbit.io/v2.0/current?";

    @Autowired
    public WeatherService(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
        this.template = new RestTemplate();
    }

    public Weather initialParametersAndRun(String country, String city) {
        this.country = country;
        this.city = city;
        return weatherQuery();
    }

    @Scheduled(cron = "0 0/15 * * * *")
    private Weather weatherQuery() {
        if (city == null && country == null)
            return null;

        System.out.println("Method Weather is call now");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(HOST)
                .queryParam("city", city)
                .queryParam("country", country)
                .queryParam("key", KEY_WEATHER);

        WeatherGeneralDTO json = template.getForObject(builder.toUriString(), WeatherGeneralDTO.class);

        assert json != null;
        Weather weather = json.getData().get(0).toWeather();
        weatherDao.save(weather);
        return weather;
    }


}
