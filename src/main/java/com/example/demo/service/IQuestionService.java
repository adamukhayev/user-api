package com.example.demo.service;

import com.example.demo.model.dto.QuestionDto;

import java.util.List;

public interface IQuestionService {

    Long addQuestion(QuestionDto questionDto);

    Long removeQuestion(Long questionId);

    Long updateQuestion(QuestionDto questionDto);

    void addAllQuestionSave(List<QuestionDto> questionDtos);
}
