package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "planets", schema = "main")
public class PlanetsEntity {

    @Id
    @Column(name = "planets_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "planets_seq")
    @SequenceGenerator(
            name = "planets_seq",
            sequenceName = "main.planets_planets_id_seq", allocationSize = 1)
    private Long planetsId;

    @Column(name = "rules_id")
    private Long rulesId;

    @Column(name = "name_of_the_planet")
    private String nameOfThePlanet;

    @Column(name = "status")
    private boolean status;

}
