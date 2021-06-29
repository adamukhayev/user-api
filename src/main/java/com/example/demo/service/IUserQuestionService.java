package com.example.demo.service;

import com.example.demo.model.dto.SurveyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserQuestionService {

    Page<SurveyDto> getAllQuestion(Pageable pageable);
}
