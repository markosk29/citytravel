package com.avangarde.citytravel.api.entities;

import lombok.*;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


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
}
