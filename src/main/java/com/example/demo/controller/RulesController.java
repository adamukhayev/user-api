package com.example.demo.controller;

import com.example.demo.model.dto.RulesDto;
import com.example.demo.service.IRulesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rules")
public class RulesController {

    private final IRulesService rulesService;

    @PostMapping
    @ApiOperation(value = "Create a rules",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> createRules(
            @RequestBody @Valid RulesDto rulesDto) {
        return ResponseEntity.ok(rulesService.createRules(rulesDto));
    }

    @GetMapping("/all/rules")
    @ApiOperation(value = "Free rulers",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Page<RulesDto>> getAllFreeRulers(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(rulesService.getAllFreeRulers(pageable));
    }

    @GetMapping("/all/youngest/ruler")
    @ApiOperation(value = "the youngest ruler",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<List<RulesDto>> getTheYoungestRuler() {
        return ResponseEntity.ok(rulesService.getTheYoungestRuler());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Show all planets of the ruler",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<RulesDto> getRuleAllPlanet(
            @PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(rulesService.getRuleAllPlanet(id));
    }
}
