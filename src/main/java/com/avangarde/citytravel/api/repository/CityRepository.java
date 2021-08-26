package com.avangarde.citytravel.api.repository;

import com.avangarde.citytravel.api.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
        Optional<City> findById(int id);
}
