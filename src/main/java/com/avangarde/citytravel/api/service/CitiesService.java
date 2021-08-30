package com.avangarde.citytravel.api.service;

import com.avangarde.citytravel.api.entities.City;
import com.avangarde.citytravel.api.mapper.CityMapper;
import com.avangarde.citytravel.api.output.CityJSON;
import com.avangarde.citytravel.api.repository.CityRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitiesService {
    private final CityRepository cityRepository;
    private List<City> route = new ArrayList<>();

    public List<City> getRoute() {
        return route;
    }

    public void setRoute(List<City> route) {
        this.route = route;
    }

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

    public CityJSON getCityByName(String name) {
        if (!cityRepository.findByName(name).isPresent()) {
            return null;
        } else {
            return CityMapper.cityToJSON(cityRepository.findByName(name).get());
        }
    }


    public List<CityJSON> getAllCities() {
        return cityRepository.findAll().stream().map(CityMapper::cityToJSON).collect(Collectors.toList());
    }

    public List<CityJSON> getNeighbours(CityJSON city) {
        return city.getNeighbours().stream().map(CityMapper::cityToJSON).collect(Collectors.toList());
    }

    public void deleteLastCity() {
        route.remove(route.size() -1);
    }

    public void addCityToRoute(String name) {
        this.route.add(cityRepository.findByName(name).get());
    }
}
