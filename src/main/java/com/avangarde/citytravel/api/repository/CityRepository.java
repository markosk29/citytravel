package com.avangarde.citytravel.api.repository;

import com.avangarde.citytravel.api.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
        Optional<City> findById(int id);
}
