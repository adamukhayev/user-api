package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.dto.PlanetsDto;
import com.example.demo.model.dto.RulesDto;
import com.example.demo.model.entity.RulesEntity;
import com.example.demo.repository.PlanetsRepository;
import com.example.demo.repository.RulesRepository;
import com.example.demo.service.IRulesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RulesImpl implements IRulesService {

    private final RulesRepository rulesRepository;
    private final PlanetsRepository planetsRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createRules(RulesDto rulesDto) {

        RulesEntity entity = rulesRepository.save(
                modelMapper.map(rulesDto, RulesEntity.class)
        );
        return entity.getRulesId();
    }

    @Override
    public Page<RulesDto> getAllFreeRulers(Pageable pageable) {
        return rulesRepository.findAllByNoneIsActiveActive(pageable)
                .map(e -> {
                    return modelMapper.map(e, RulesDto.class);
                });
    }

    @Override
    public List<RulesDto> getTheYoungestRuler() {
        return rulesRepository.findFirst10ByOrderByAgeAsc()
                .stream().map(e -> {
                    return modelMapper.map(e, RulesDto.class);
                }).collect(Collectors.toList());
    }

    @Override
    public RulesDto getRuleAllPlanet(Long id) {

        return rulesRepository.findByRulesId(id)
                .map(e -> {
                    var dto = modelMapper.map(e, RulesDto.class);
                    dto.setPlanetsDto(
                            planetsRepository
                            .findAllByRulesId(dto.getRulesId())
                            .stream()
                            .map(p -> {
                                return modelMapper.map(p, PlanetsDto.class);
                            }).collect(Collectors.toList())
                    );
                    return dto;
                }).orElseThrow(() ->
                        new GeneralTestApiException(TestApiError
                                .RULER_DOES_NOT_EXIST));
    }
}
