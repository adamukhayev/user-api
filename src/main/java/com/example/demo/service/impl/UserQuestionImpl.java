package com.example.demo.service.impl;

import com.example.demo.model.dto.SurveyDto;
import com.example.demo.model.entity.SurveyEntity;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.service.IUserQuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQuestionImpl implements IUserQuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<SurveyDto> getAllQuestion(Pageable pageable) {
        Page<SurveyEntity> surveyEntities = surveyRepository.findAllByNoneStatusDeleted(pageable);

        return surveyEntities.map(e -> modelMapper.map(e, SurveyDto.class));
    }
}
