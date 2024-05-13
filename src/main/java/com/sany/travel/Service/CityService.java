package com.sany.travel.Service;

import com.sany.travel.Persistence.CityRepository;
import com.sany.travel.Persistence.Entities.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCity(String name) {
        return cityRepository.findByName(name);
    }
}
