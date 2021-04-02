package com.example.demo.service;

import com.example.demo.model.dto.PlanetsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPlanetsService {

    Long createPlanet(PlanetsDto planetsDto);

    Page<PlanetsDto> getAllFreePlanets(Pageable pageable);

    PlanetsDto getPlanets(Long planetsId);

}
