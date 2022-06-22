package com.example.demo.model.dto;

import com.example.demo.model.Role;
import com.example.demo.model.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String email;
    private String password;
    private Status isActive;
    private Role role;
}
