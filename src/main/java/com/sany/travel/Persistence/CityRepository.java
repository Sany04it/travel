package com.sany.travel.Persistence;

import com.sany.travel.Persistence.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {

    City findByName(String name);
}
