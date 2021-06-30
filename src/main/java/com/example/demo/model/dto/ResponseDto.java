package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private Long surveyId;

    private String title;

    private String description;

    private List<ResponseAnswer> responseQuestions;
}
