package com.sany.travel.Service;

import com.sany.travel.Persistence.Entities.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherInfo getWeatherInfo(String cityName) {
        String apiUrl = "https://api.weatherapi.com/v1/current.json?q=" + cityName + "?&key=" + apiKey;
        try {
            ResponseEntity<WeatherInfo> response = restTemplate.getForEntity(apiUrl, WeatherInfo.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                // TODO
                return null;
            }
        } catch (RestClientException e) {
            // TODO
            return null;
        }
    }
}

