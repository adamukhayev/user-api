package com.example.demo.controller;

import com.example.demo.model.dto.PlanetsDto;
import com.example.demo.service.IPlanetsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planets")
public class PlanetsController {

    private final IPlanetsService planetsService;

    @PostMapping
    @ApiOperation(value = "Create a planet",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> createPlanet(
            @RequestBody @Valid PlanetsDto planetsDto) {
        return ResponseEntity.ok(planetsService.createPlanet(planetsDto));
    }

    @GetMapping("/all/free/planets")
    @ApiOperation(value = "Free planets",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Page<PlanetsDto>> getAllFreePlanets(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(planetsService.getAllFreePlanets(pageable));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get planet",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<PlanetsDto> getPlanet(
            @PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(planetsService.getPlanets(id));
    }

}
