package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "answer", schema = "main")
public class AnswerEntity {

    @Id
    @Column(name = "answer_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_seq")
    @SequenceGenerator(
            name = "answer_seq",
            sequenceName = "main.answer_answer_id_seq", allocationSize = 1)
    private Long answerId;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "question_id")
    private Long questionId;

}
