package com.example.demo.controller.authentication;

import com.example.demo.exeptions.GeneralTestApiException;
import com.example.demo.exeptions.TestApiError;
import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.auth.AuthenticationDto;
import com.example.demo.model.auth.ResponseUserDto;
import com.example.demo.model.auth.UserDto;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation(value = "Registration/Login")
@RequestMapping("/api/v1/auth")
@Validated
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JavaMailSender emailSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "login")
    @PostMapping("/login")
    public ResponseEntity<ResponseUserDto> authenticate(@RequestBody AuthenticationDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(),
                            request.getPassword()));
            UserEntity userEntity = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new GeneralTestApiException(TestApiError.E500_NOT_FOUND,
                            TestApiError.E500_NOT_FOUND.toString()));

            var role = roleRepository.findByUserId(userEntity.getUserId());
            String token = jwtTokenProvider.createToken(request.getEmail(),
                    role.getRole().name());

            return ResponseEntity.ok(ResponseUserDto
                    .builder()
                    .email(request.getEmail())
                    .token(token)
                    .build());

        } catch (AuthenticationException e) {
            throw new GeneralTestApiException(TestApiError.E403_FORBIDDEN,
                    TestApiError.E403_FORBIDDEN.toString());
        }
    }

    @ApiOperation(value = "logout")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler =
                new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "register")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new GeneralTestApiException(TestApiError.E500_ALREADY_EXIST,
                    TestApiError.E500_ALREADY_EXIST.toString());
        }

        UserEntity entity = new UserEntity();
        entity.setEmail(userDto.getEmail());
        entity.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12)));
        entity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setIsActive(Status.ACTIVE);

        Long userId = userRepository.save(entity).getUserId();

        roleRepository.save(RoleEntity.builder().role(Role.USER).userId(userId).build());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/captcha")
    public ResponseEntity<String> sendEmail(@RequestParam String email) {

        var message = new SimpleMailMessage();

        var captcha = new Random().nextInt(999999);
        message.setTo(email);
        message.setSubject("Код для потверждения почты");
        message.setText("Код : " + captcha);

        emailSender.send(message);

        return ResponseEntity.ok(String.valueOf(captcha));
    }

}
