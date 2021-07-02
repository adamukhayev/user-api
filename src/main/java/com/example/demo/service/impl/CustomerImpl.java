package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.TypeEnum;
import com.example.demo.model.dto.Answer;
import com.example.demo.model.dto.ResponseAnswer;
import com.example.demo.model.dto.ResponseDto;
import com.example.demo.model.entity.CustomerEntity;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (customerEntities.isEmpty()) {
            throw new GeneralTestApiException(TestApiError.E500_NOT_FOUND);
        }

        List<ResponseDto> responseDtos = customerEntities.stream().map(user -> {
            ResponseDto responseDto = new ResponseDto();
            surveyRepository.findAllBySurveyId(user.getSurveyId())
                    .stream().map(surveyEntity -> {
                responseDto.setDescription(surveyEntity.getDescription());
                responseDto.setTitle(surveyEntity.getTitle());
                List<ResponseAnswer> responseAnswers = questionRepository
                        .findAllByQuestionId(user.getQuestionId())
                        .stream()
                        .map(questionEntity -> {
                            ResponseAnswer responseAnswer = new ResponseAnswer();
                            responseAnswer.setQuestion(questionEntity.getQuestion());
                            responseAnswer.setTitle(questionEntity.getTitle());
                            if (questionEntity.getQuestionType().equals(TypeEnum.TWO)) {
                                List<Answer> answerList = answerRepository
                                        .findAllByAnswerIdAndQuestionId(
                                                user.getAnswerId(),
                                                user.getQuestionId())
                                        .stream().map(answerEntity -> {
                                            Answer answers = new Answer();
                                            answers.setAnswer(answerEntity.getAnswerText());
                                            return answers;
                                        }).collect(Collectors.toList());
                                responseAnswer.setAnswer(answerList);
                                return responseAnswer;
                            } else {
                                Answer answer = new Answer();
                                List<Answer> answerList = answerRepository
                                        .findAllByAnswerIdAndQuestionId(
                                                user.getAnswerId(),
                                                user.getQuestionId())
                                        .stream()
                                        .map(answerEntity -> {
                                            if (Objects.isNull(answerEntity)) {
                                                throw new GeneralTestApiException(TestApiError.E500_ANSWER_NOT_FOUND);
                                            } else {
                                                answer.setAnswer(answerEntity.getAnswerText());

                                                return answer;
                                            }
                                        }).collect(Collectors.toList());
                                responseAnswer.setAnswer(answerList);
                                return responseAnswer;
                            }
                        }).collect(Collectors.toList());
                responseDto.setResponseQuestions(responseAnswers);
                return responseDto;
            }).collect(Collectors.toList());
            return responseDto;
        }).collect(Collectors.toList());
        return responseDtos;
    }
}
