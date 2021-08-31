package com.avangarde.citytravel.api.output;

import com.avangarde.citytravel.api.entities.City;
import lombok.*;

import java.io.Serializable;
import java.util.List;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class CityJSON implements Serializable {

    private String name;
    private List<City> neighbours;

    }
