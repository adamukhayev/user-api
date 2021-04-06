package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.Status;
import com.example.demo.repository.PlanetsRepository;
import com.example.demo.repository.RulesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserImpl implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PlanetsRepository planetsRepository;
    private final RulesRepository rulesRepository;


    @Override
    public Long appointRuler(Long rulesId, Long planetsId) {

        return planetsRepository.findByPlanetsId(planetsId)
                .map(e -> {
                    if(!e.isStatus()) {
                        rulesRepository.findByRulesId(rulesId)
                                .map(r -> {
                                    r.setIsActive(Status.ACTIVE);
                                    rulesRepository.save(r);
                                    return e.getRulesId();
                                }).orElseThrow(() ->
                                new GeneralTestApiException(TestApiError.RULER_DOES_NOT_EXIST));
                        e.setRulesId(rulesId);
                        e.setStatus(true);
                        planetsRepository.save(e);
                        return rulesId;
                    } else  {
                        throw new GeneralTestApiException(TestApiError.THE_PLANET_HAS_ITS_RULER);
                    }
                }).orElseThrow(() ->
                    new GeneralTestApiException(TestApiError.THE_PLANET_DOES_NOT_EXIST)
                );
    }

    @Override
    public Long updatePlanetRuler(Long rulesId, Long planetId) {
        return planetsRepository.findByPlanetsId(planetId)
                .map(e -> {
                    e.setRulesId(rulesId);
                    e.setStatus(true);
                    planetsRepository.save(e);
                    return e.getPlanetsId();
                }).orElseThrow(() ->
                    new GeneralTestApiException(TestApiError.THE_PLANET_DOES_NOT_EXIST)
                );
    }

    @Override
    public Long deletePlanet(Long id) {

        return planetsRepository.findByPlanetsId(id)
                .map(e -> {
                    planetsRepository.deleteById(e.getPlanetsId());
                    return e.getPlanetsId();
                }).orElseThrow(() ->
                        new GeneralTestApiException(TestApiError.THE_PLANET_DOES_NOT_EXIST)
                );
    }

    @Override
    public Long removeRuler(Long rulesId, Long planetsId) {
        return planetsRepository.findByPlanetsId(planetsId)
                .map(e -> {
                    if (e.isStatus()) {
                        e.setRulesId(null);
                        e.setStatus(false);
                        planetsRepository.save(e);
                        if (planetsRepository.findAllByRulesId(rulesId).size() == 0) {
                            rulesRepository.findByRulesId(rulesId)
                                    .map(r -> {
                                        r.setIsActive(Status.INACTIVE);
                                        rulesRepository.save(r);
                                        return r.getRulesId();
                                    });
                        }
                        return e.getPlanetsId();
                    } else {
                        throw new GeneralTestApiException(TestApiError.PLANETS_NO_RULER);
                    }
                }).orElseThrow(() ->
                        new GeneralTestApiException(TestApiError.THE_PLANET_DOES_NOT_EXIST));
    }

}
