package com.avangarde.citytravel.api.service;

import com.avangarde.citytravel.api.entities.City;
import com.avangarde.citytravel.api.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesService {
    private final CityRepository cityRepository;

    public CitiesService(CityRepository cityRepository) {

        this.cityRepository = cityRepository;
    }

    public City getCity(int id) {
        if (!cityRepository.findById(id).isPresent()) {
            return null;
        } else {
            return cityRepository.findById(id).get();
        }
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
/*
    public List<City> getAllNeighbours() {
       return cityRepository.findNeighbours();
    }*/

    public void addVehicle(City city) {
        cityRepository.save(city);
    }

    public void deleteVehicle(City city) {
        cityRepository.delete(city);
    }
}
