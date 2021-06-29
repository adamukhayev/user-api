package com.example.demo.model.entity;

import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "survey", schema = "main")
public class SurveyEntity {

    @Id
    @Column(name = "survey_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_seq")
    @SequenceGenerator(
            name = "survey_seq",
            sequenceName = "main.survey_survey_id_seq", allocationSize = 1)
    private Long surveyId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany
    @JoinColumn(name = "survey_id", insertable = false, updatable = false)
    private List<QuestionEntity> question;

}
