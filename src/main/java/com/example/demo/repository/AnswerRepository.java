package com.example.demo.repository;

import com.example.demo.model.entity.AnswerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository extends PagingAndSortingRepository<AnswerEntity, Long> {

    AnswerEntity findByAnswerId(Long answerId);

    boolean existsByAnswerIdAndQuestionId(Long answerId, Long questionId);

    boolean existsByAnswerId(Long answerId);

    void deleteByAnswerId(Long answerId);

    List<AnswerEntity> findAllByAnswerId(Long answerId);

    List<AnswerEntity> findAllByAnswerIdAndQuestionId(Long answerId, Long questionId);

    AnswerEntity findByAnswerIdAndQuestionId(Long answerId, Long questionId);
}
