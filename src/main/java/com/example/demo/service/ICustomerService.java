package com.example.demo.service;


import com.example.demo.model.dto.SurveyDto;

import java.util.List;

public interface ICustomerService {
    void response(Long userId, Long questionId, Long answerId, Long surveyId);

    List<SurveyDto> getResult(Long userId);
}
