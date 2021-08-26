package com.avangarde.citytravel.api.controller;

import com.avangarde.citytravel.api.repository.CityRepository;
import com.avangarde.citytravel.api.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CitiesController {
    private final CityRepository cityRepository;
    private final CitiesService citiesService;

    @Autowired
    public CitiesController(CityRepository citiesRepository, CitiesService citiesService) {
        this.cityRepository = citiesRepository;
        this.citiesService = citiesService;
    }
}
