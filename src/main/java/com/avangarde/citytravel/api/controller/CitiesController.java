package com.avangarde.citytravel.api.controller;

import com.avangarde.citytravel.api.entities.City;
import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class CitiesController {
    private final CitiesService citiesService;

    @Autowired
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getCity(@PathVariable int id) {
        if (id > 0) {
            City city = citiesService.getCity(id);

            if (city != null) {
                return ResponseEntity.ok().body(citiesService.getCity(id));
            } else {
                return ResponseEntity.ok().body("City with id " + id + " not found!");
            }

        } else {
            return ResponseEntity.badRequest().body("Invalid city id.");
        }
    }

    @GetMapping("/city/all")
    public ResponseEntity<?> getAllCities() {
        List<City> cities = citiesService.getAllCities();

        if (cities.size() > 0) {
            return ResponseEntity.ok().body(cities);
        } else {
            return ResponseEntity.ok().body("No cities available.");
        }
    }

}
