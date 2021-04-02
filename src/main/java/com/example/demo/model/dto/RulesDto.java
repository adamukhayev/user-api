package com.example.demo.model.dto;

import com.example.demo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RulesDto {

    private Long rulesId;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    private Status isActive;

    private List<PlanetsDto> planetsDto;
}
