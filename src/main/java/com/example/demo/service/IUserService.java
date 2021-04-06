package com.example.demo.service;


public interface IUserService {

    Long appointRuler(Long rulesId, Long planetsId);

    Long updatePlanetRuler(Long rulesId, Long planetId);

    Long deletePlanet(Long id);

    Long removeRuler(Long rulesId, Long planetsId);
}
