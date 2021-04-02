package com.example.demo.controller;

import com.example.demo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('write')")
public class UserController {

    private final IUserService userService;

    @PostMapping("/appoint/ruler")
    @ApiOperation(value = "Appoint a ruler",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> appointRuler(
            @RequestParam @NotNull Long rulesId,
            @RequestParam @NotNull Long planetsId
    ) {
        return ResponseEntity.ok(userService.appointRuler(rulesId, planetsId));
    }

    @PutMapping("/remove/ruler/{id}")
    @ApiOperation(value = "Remove the post of ruler",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> removeRuler(
            @PathVariable("id") @NotNull Long planetsId
    ) {
        return ResponseEntity.ok(userService.removeRuler(planetsId));
    }

    @PutMapping
    @ApiOperation(value = "Change the ruler of the planet", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> updatePlanetRuler(
            @RequestParam @NotNull Long rulesId,
            @RequestParam @NotNull Long planetId) {
        return ResponseEntity.ok(userService.updatePlanetRuler(rulesId, planetId));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete planet",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> deletePlanet(
            @PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(userService.deletePlanet(id));
    }
}


