package com.example.demo.repository;

import com.example.demo.model.entity.SurveyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends PagingAndSortingRepository<SurveyEntity, Long> {
    List<SurveyEntity> findAllBySurveyId(Long surveyId);

    SurveyEntity findBySurveyId(Long surveyId);

    @Query("SELECT r FROM SurveyEntity r WHERE r.status <> 'DELETED'")
    Page<SurveyEntity> findAllByNoneStatusDeleted(Pageable pageable);
}
