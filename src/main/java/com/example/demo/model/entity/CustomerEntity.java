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
@Table(name = "customer", schema = "main")
public class CustomerEntity {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_seq")
    @SequenceGenerator(
            name = "customer_seq",
            sequenceName = "main.customer_customer_id_seq", allocationSize = 1)
    private Long customerId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "answer_id")
    private Long answerId;
}
