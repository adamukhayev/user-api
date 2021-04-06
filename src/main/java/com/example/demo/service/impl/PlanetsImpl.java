package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.dto.PlanetsDto;
import com.example.demo.model.entity.PlanetsEntity;
import com.example.demo.repository.PlanetsRepository;
import com.example.demo.service.IPlanetsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetsImpl implements IPlanetsService {

    private final PlanetsRepository planetsRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createPlanet(PlanetsDto planetsDto) {

        planetsDto.setStatus(false);
        PlanetsEntity entity = planetsRepository.save(
                modelMapper.map(planetsDto, PlanetsEntity.class)
        );
        return entity.getPlanetsId();
    }

    @Override
    public Page<PlanetsDto> getAllFreePlanets(Pageable pageable) {

        return planetsRepository.findAllByNoneStatusTrue(pageable)
                .map(e -> {
                    var dto = modelMapper.map(e, PlanetsDto.class);
                    return dto;
                });

    }

    @Override
    public PlanetsDto getPlanets(Long planetsId) {
        return modelMapper.map(
                planetsRepository
                        .findByPlanetsId(planetsId).
                        map(e -> {
                                return modelMapper.map(e, PlanetsDto.class);
                        }).orElseThrow(() ->
                        new GeneralTestApiException(TestApiError.THE_PLANET_DOES_NOT_EXIST)
                ), PlanetsDto.class);
    }

}
