package com.example.demo.model.entity;

import com.example.demo.model.Status;
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
@Table(name = "rules", schema = "main")
public class RulesEntity {

    @Id
    @Column(name = "rules_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rules_seq")
    @SequenceGenerator(
            name = "rules_seq",
            sequenceName = "main.rules_rules_id_seq", allocationSize = 1)
    private Long rulesId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "is_active")
    @Enumerated(value = EnumType.STRING)
    private Status isActive;
}
