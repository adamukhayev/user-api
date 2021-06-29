package com.example.demo.repository;

import com.example.demo.model.entity.QuestionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<QuestionEntity, Long> {

    boolean existsByQuestionId(Long questionId);

    QuestionEntity findByQuestionId(Long questionId);

    List<QuestionEntity> findAllByQuestionId(Long questionId);

    List<QuestionEntity> findAllBySurveyId(Long surveyId);
}
