package com.example.demo.model.entity;

import com.example.demo.model.Status;
import com.example.demo.model.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "question", schema = "main")
public class QuestionEntity {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_seq")
    @SequenceGenerator(
            name = "question_seq",
            sequenceName = "main.question_question_id_seq", allocationSize = 1)
    private Long questionId;

    @Column(name = "title")
    private String title;

    @Column(name = "question")
    private String question;

    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "question_type")
    @Enumerated(value = EnumType.STRING)
    private TypeEnum questionType;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private List<AnswerEntity> answer;

}
