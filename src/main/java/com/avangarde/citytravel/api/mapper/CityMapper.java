package com.avangarde.citytravel.api.mapper;

import com.avangarde.citytravel.api.entities.City;
import com.avangarde.citytravel.api.model.CityInput;
import com.avangarde.citytravel.api.output.CityJSON;

public class CityMapper {

    public static City inputToCity(CityInput input) {
        return City.builder()
                .name(input.getName())
                .neighbours(input.getNeighbours())
                .build();
    }

    public static CityJSON cityToJSON(City city) {
        return CityJSON.builder()
                .name(city.getName())
                .neighbours(city.getNeighbours())
                .build();
    }
}
