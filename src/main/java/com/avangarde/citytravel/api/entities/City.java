package com.avangarde.citytravel.api.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities", schema = "cityapp")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;

    @Column(name="city_name", length=50, nullable=false, unique=true)
    private String name;

    @OneToMany(mappedBy = "current")
    private List<CityNeighbourRelation> neighbours;

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", neighbours=" + neighbours +
                '}';
    }
}
