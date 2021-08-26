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
@Entity(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;

    @Column(name="city_name", length=50, nullable=false, unique=true)
    private String name;


    @ManyToMany
    @JoinTable(
            name = "cityneighbours",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "neighbour_id")
    )
    private Set<City> neighbours;
}
