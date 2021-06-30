package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.TypeEnum;
import com.example.demo.model.dto.Answer;
import com.example.demo.model.dto.ResponseAnswer;
import com.example.demo.model.dto.ResponseDto;
import com.example.demo.model.entity.AnswerEntity;
import com.example.demo.model.entity.CustomerEntity;
import com.example.demo.model.entity.QuestionEntity;
import com.example.demo.model.entity.SurveyEntity;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.QuestionRepository;
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
    private final QuestionRepository questionRepository;
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
    public List<ResponseDto> getResult(Long userId) {
        List<CustomerEntity> customerEntities = customerRepository.findAllByUserId(userId);
        List<ResponseDto> surveyDtos = new ArrayList<>();
        for (CustomerEntity e : customerEntities) {
            List<SurveyEntity> dto = surveyRepository.findAllBySurveyId(e.getSurveyId());
            for (int i = 0; i < dto.size(); i++) {
                ResponseDto resDto = new ResponseDto();
                resDto.setDescription(dto.get(i).getTitle());
                resDto.setTitle(dto.get(i).getDescription());
                List<QuestionEntity> questionEntity = questionRepository
                        .findAllBySurveyIdAndQuestionId(e.getSurveyId(), e.getQuestionId());
                List<ResponseAnswer> answerList = new ArrayList<>();
                for (int j = 0; j < questionEntity.size(); j++) {
                    ResponseAnswer responseAnswer = new ResponseAnswer();
                    responseAnswer.setQuestion(questionEntity.get(j).getQuestion());
                    responseAnswer.setTitle(questionEntity.get(j).getTitle());
                    if (questionEntity.get(j).getTitle().equals(TypeEnum.TWO.name())) {
                        List<AnswerEntity> answerEntities = answerRepository
                                .findAllByAnswerIdAndQuestionId(e.getAnswerId(), e.getQuestionId());
                        List<Answer> answers = new ArrayList<>();
                        for (int z = 0; z < answerEntities.size(); z++) {
                            Answer answer = new Answer();
                            answer.setAnswer(answerEntities.get(z).getAnswerText());
                            answers.add(answer);
                            responseAnswer.setAnswer(answers);
                        }
                    } else {
                        List<Answer> answers = new ArrayList<>();
                        AnswerEntity answerEntity = answerRepository
                                .findByAnswerIdAndQuestionId(e.getAnswerId(), e.getQuestionId());
                        Answer answer = new Answer();
                        answer.setAnswer(answerEntity.getAnswerText());
                        answers.add(answer);
                        responseAnswer.setAnswer(answers);
                    }
                    answerList.add(responseAnswer);
                    resDto.setResponseQuestions(answerList);
                    surveyDtos.add(resDto);
                }
            }
        }
        return surveyDtos;
    }
}
