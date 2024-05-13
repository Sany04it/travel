package com.sany.travel.Controller;

import com.sany.travel.Persistence.Entities.City;
import com.sany.travel.Persistence.Entities.WeatherInfo;
import com.sany.travel.Service.CityService;
import com.sany.travel.Service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;
    private final WeatherService weatherService;

    public CityController(CityService cityService, WeatherService weatherService) {
        this.cityService = cityService;
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{name}")
    public City getCity(@PathVariable String name) {
        return cityService.getCity(name);
    }

    @GetMapping("/{name}/weather")
    public WeatherInfo getWeatherInfo(@PathVariable String name) {
        return weatherService.getWeatherInfo(name);
    }
}