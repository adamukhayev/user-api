package com.example.demo.model.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUserDto {

    private String email;
    private String token;
}
