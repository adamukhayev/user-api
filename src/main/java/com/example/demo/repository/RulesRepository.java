package com.example.demo.repository;

import com.example.demo.model.entity.RulesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RulesRepository extends PagingAndSortingRepository<RulesEntity, Long> {

    @Query("SELECT r FROM RulesEntity r WHERE r.isActive <> 'ACTIVE'")
    Page<RulesEntity> findAllByNoneIsActiveActive(Pageable pageable);

    List<RulesEntity> findFirst10ByOrderByAgeAsc();

    Optional<RulesEntity> findByRulesId(Long rulesId);
}
