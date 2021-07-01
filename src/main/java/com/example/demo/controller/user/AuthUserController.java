package com.example.demo.controller.user;

import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth")
public class AuthUserController {

    private final UserRepository userRepository;

    @ApiOperation(value = "register")
    @PostMapping("/register")
    public void register(@RequestBody @Valid UserDto userDto) {
        UserEntity entity = new UserEntity();

        entity.setEmail(userDto.getEmail());
        entity.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12)));
        entity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setIsActive(Status.ACTIVE);
        entity.setRole(Role.USER);

        userRepository.save(entity);
    }
}
