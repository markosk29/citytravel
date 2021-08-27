package com.avangarde.citytravel.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cityneighbours", schema = "cityapp")
public class CityNeighbourRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter
    @Setter
    private int neighbour_id;

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            nullable = false
    )
    private City current;

}
