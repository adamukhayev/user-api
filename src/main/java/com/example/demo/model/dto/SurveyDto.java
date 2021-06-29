package com.example.demo.model.dto;

import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SurveyDto {

    private Long surveyId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private LocalDateTime createDate;

    private LocalDateTime end_date;

    private Status status;

    private List<QuestionDto> question;
}
