package com.avangarde.citytravel.api.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name="city_neighbours",
            joinColumns={@JoinColumn(name="city_id")},
            inverseJoinColumns={@JoinColumn(name="neighbour_id")})
    private List<City> neighbours;

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", neighbours=" + neighbours +
                '}';
    }
}
