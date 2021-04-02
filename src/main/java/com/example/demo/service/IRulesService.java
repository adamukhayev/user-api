package com.example.demo.service;

import com.example.demo.model.dto.RulesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRulesService {
    Long createRules(RulesDto rulesDto);

    Page<RulesDto> getAllFreeRulers(Pageable pageable);

    List<RulesDto> getTheYoungestRuler();

    RulesDto getRuleAllPlanet(Long id);
}
