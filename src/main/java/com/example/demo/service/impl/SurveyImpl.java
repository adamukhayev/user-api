package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.Status;
import com.example.demo.model.dto.AnswerDto;
import com.example.demo.model.dto.QuestionDto;
import com.example.demo.model.dto.SurveyDto;
import com.example.demo.model.entity.AnswerEntity;
import com.example.demo.model.entity.QuestionEntity;
import com.example.demo.model.entity.SurveyEntity;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.service.ISurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SurveyImpl implements ISurveyService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;
    private final SurveyRepository surveyRepository;

    @Override
    public Long addSurvey(SurveyDto surveyDto) {
        SurveyEntity surveyEntity = modelMapper.map(surveyDto, SurveyEntity.class);
        surveyEntity.setStatus(Status.ACTIVE);
        surveyEntity.setCreateDate(LocalDateTime.now());
        surveyEntity.setEndDate(LocalDateTime.now());
        Long id = surveyRepository.save(surveyEntity).getSurveyId();
        if (!surveyDto.getQuestion().isEmpty()) {
            List<QuestionDto> questionDtos = surveyDto.getQuestion();
            for (QuestionDto questionDto : questionDtos) {
                QuestionEntity entity = modelMapper.map(questionDto, QuestionEntity.class);
                entity.setSurveyId(id);
                entity.setStatus(Status.ACTIVE);
                Long questionId = questionRepository.save(entity).getQuestionId();
                if (!entity.getAnswer().isEmpty()) {
                    List<AnswerEntity> answerEntities = entity.getAnswer();
                    for (AnswerEntity answerEntity : answerEntities) {
                        answerEntity.setQuestionId(questionId);
                        answerRepository.save(answerEntity);
                    }
                }
            }
        }
        return id;
    }


    @Override
    public Long removeSurvey(Long surveyId) {
        SurveyEntity entity = surveyRepository.findBySurveyId(surveyId);
        if (Objects.nonNull(entity)) {
            entity.setStatus(Status.DELETED);
            surveyRepository.save(entity);
            List<QuestionEntity> questionEntities = questionRepository.findAllBySurveyId(surveyId);
            for (QuestionEntity e : questionEntities) {
                e.setStatus(Status.DELETED);
            }
            return surveyId;
        } else {
            throw new GeneralTestApiException(TestApiError.E500_NOT_FOUND);
        }
    }

    @Override
    public Long updateSurvey(SurveyDto surveyDto) {
        SurveyEntity entity = surveyRepository.findBySurveyId(surveyDto.getSurveyId());
        if (Objects.nonNull(entity)) {
            SurveyEntity surveyEntity = modelMapper.map(surveyDto, SurveyEntity.class);
            surveyEntity.setStatus(Status.ACTIVE);
            surveyEntity.setCreateDate(entity.getCreateDate());
            surveyEntity.setEndDate(LocalDateTime.now());
            Long id = surveyRepository.save(surveyEntity).getSurveyId();
            if (!surveyDto.getQuestion().isEmpty()) {
                questionRepository
                        .saveAll(getQuestionEntity(
                                surveyDto.getQuestion(),
                                surveyDto.getSurveyId()));
            }

            for (int i = 0; i < surveyDto.getQuestion().size(); i++) {
                if (!surveyDto.getQuestion().isEmpty()) {
                    answerRepository.saveAll(getAnswerEntity(surveyDto.getQuestion().get(i).getAnswer()
                            , surveyDto.getQuestion().get(i).getQuestionId()));
                }
            }
            return id;
        } else {
            throw new GeneralTestApiException(TestApiError.E500_NOT_FOUND);
        }
    }

    public List<AnswerEntity> getAnswerEntity(List<AnswerDto> list, Long id) {
        return list.stream()
                .map(e -> {
                    AnswerEntity answerEntity = modelMapper.map(e, AnswerEntity.class);
                    answerEntity.setQuestionId(id);
                    return answerEntity;
                }).collect(Collectors.toList());
    }

    public List<QuestionEntity> getQuestionEntity(List<QuestionDto> list, Long id) {
        return list.stream()
                .map(e -> {
                    QuestionEntity questionEntity = modelMapper.map(e, QuestionEntity.class);
                    questionEntity.setSurveyId(id);
                    questionEntity.setStatus(Status.ACTIVE);
                    return questionEntity;
                }).collect(Collectors.toList());
    }
}
