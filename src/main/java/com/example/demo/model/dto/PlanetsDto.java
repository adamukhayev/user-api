package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlanetsDto {

    private Long planetsId;

    private Long rulesId;

    @NotBlank
    private String nameOfThePlanet;

    private boolean status;
}
