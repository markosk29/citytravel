package com.avangarde.citytravel.api.model;

import com.avangarde.citytravel.api.entities.City;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityInput {

        private String name;
        private List<City> neighbours;
    }
