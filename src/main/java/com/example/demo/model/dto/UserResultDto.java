package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResultDto {

    private List<SurveyDto> surveyDtos;

    private String userResponse;

    private String response;

    private String type;
}
