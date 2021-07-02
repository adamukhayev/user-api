package com.example.demo.service.impl;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.dto.AnswerDto;
import com.example.demo.model.entity.AnswerEntity;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.service.IAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AnswerImpl implements IAnswerService {

    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;


    @Override
    public Long addAnswer(AnswerDto answerDto) {
        return answerRepository
                .save(modelMapper.map(
                        answerDto,
                        AnswerEntity.class))
                .getAnswerId();
    }

    @Override
    public Long removeAnswer(Long answerId) {
        if (answerRepository.existsByAnswerId(answerId)) {
            answerRepository.deleteByAnswerId(answerId);
            return answerId;
        } else {
            throw new GeneralTestApiException(TestApiError.E500_ANSWER_NOT_FOUND);
        }
    }

    @Override
    public Long updateAnswer(AnswerDto answerDto) {
        if (answerRepository.existsByAnswerId(
                answerDto.getAnswerId())) {
            return addAnswer(answerDto);
        } else {
            throw new GeneralTestApiException(TestApiError.E500_ANSWER_NOT_FOUND);
        }
    }

    @Override
    public void allSaveAnswer(List<AnswerDto> answerDto) {
        List<AnswerEntity> entities = new ArrayList<>();

        for (AnswerDto d : answerDto) {
            AnswerEntity answerEntity = modelMapper.map(d, AnswerEntity.class);
            entities.add(answerEntity);
        }
        try {
            answerRepository.saveAll(entities);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
