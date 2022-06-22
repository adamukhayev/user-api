package com.example.demo.controller;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.dto.UserDto;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {

        var entity = repository.findByEmail(email)
                .orElseThrow(() ->
                        new GeneralTestApiException(TestApiError.E500_NOT_FOUND,
                                "User doesn't exists"));
        var role = roleRepository.findByUserId(entity.getUserId());

        UserDto userDto = UserDto.builder()
                .email(email)
                .password(entity.getPassword())
                .isActive(entity.getIsActive())
                .role(role.getRole())
                .build();

        return ResponseEntity.ok(userDto);
    }
}
