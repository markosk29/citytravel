package com.avangarde.citytravel.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "cityneighbours")
public class CityNeighbourRelation {

    @Column(name="city_id", length=50, nullable=false, unique=false)
    private int cityId;

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "city_id",
            nullable = false
    )
    @Column(name="neighbour_id", length=50, nullable=false, unique=false)
    private int neighbourId;
}
