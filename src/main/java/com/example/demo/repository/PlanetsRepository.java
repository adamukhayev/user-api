package com.example.demo.repository;

import com.example.demo.model.entity.PlanetsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlanetsRepository extends PagingAndSortingRepository<PlanetsEntity, Long> {

    @Query("SELECT p FROM PlanetsEntity p where p.status <> true")
    Page<PlanetsEntity> findAllByNoneStatusTrue(Pageable pageable);

    List<PlanetsEntity> findAllByRulesId(Long rulesId);

    Optional<PlanetsEntity> findByPlanetsId(Long id);
}
