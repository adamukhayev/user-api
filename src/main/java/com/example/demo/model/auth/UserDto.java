package com.example.demo.model.auth;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@ToString
@Validated
public class UserDto {

    private Long userId;

    @NotBlank
    @Pattern(regexp = "\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", message = "Введите корректный адрес электрной почты")
    private String email;

    @NotBlank
    @Size(min = 6, message = "Минимальное количесиво символов 6")
    private String password;

    private Date createDate;

}
