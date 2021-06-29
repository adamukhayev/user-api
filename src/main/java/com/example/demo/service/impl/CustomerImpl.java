package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.dto.SurveyDto;
import com.example.demo.model.entity.CustomerEntity;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;
    private final SurveyRepository surveyRepository;

    @Override
    public void response(Long userId, Long questionId, Long answerId, Long surveyId) {
        if (answerRepository.existsByAnswerIdAndQuestionId(answerId, questionId)) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setUserId(userId);
            customerEntity.setQuestionId(questionId);
            customerEntity.setAnswerId(answerId);
            customerEntity.setSurveyId(surveyId);

            customerRepository.save(customerEntity);
        } else {
            throw new GeneralTestApiException(TestApiError.E500_ANSWER_NOT_FOUND);
        }
    }

    @Override
    public List<SurveyDto> getResult(Long userId) {
        List<CustomerEntity> customerEntities = customerRepository.findAllByUserId(userId);
        List<SurveyDto> surveyDtos = new ArrayList<>();
        for (CustomerEntity e : customerEntities) {
            SurveyDto dto = modelMapper.map(surveyRepository.findBySurveyId(e.getSurveyId()), SurveyDto.class);
            surveyDtos.add(dto);
        }
        return surveyDtos;
    }
}
