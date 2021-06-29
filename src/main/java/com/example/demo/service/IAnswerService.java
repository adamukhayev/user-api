package com.example.demo.service;

import com.example.demo.model.dto.AnswerDto;

import java.util.List;

public interface IAnswerService {
    Long addAnswer(AnswerDto answerDto);

    Long removeAnswer(Long answerId);

    Long updateAnswer(AnswerDto answerDto);

    void allSaveAnswer(List<AnswerDto> answerDto);

}
