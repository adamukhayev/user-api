package com.example.demo.model.dto;

import com.example.demo.model.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDto {

    private Long questionId;

    @NotBlank
    private String title;

    @NotBlank
    private String question;

    private Long surveyId;

    private TypeEnum questionType;

    private List<AnswerDto> answer;
}