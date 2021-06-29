package com.example.demo.service;

import com.example.demo.model.dto.SurveyDto;

public interface ISurveyService {
    Long addSurvey(SurveyDto surveyDto);

    Long removeSurvey(Long questionId);

    Long updateSurvey(SurveyDto surveyDto);
}
