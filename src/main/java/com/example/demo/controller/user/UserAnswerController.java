package com.example.demo.controller.user;

import com.example.demo.model.dto.ResponseDto;
import com.example.demo.service.ICustomerService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/answer")
public class UserAnswerController {

    private final ICustomerService customerService;

    @PostMapping
    @ApiOperation(value = "Response")
    public ResponseEntity<Void> response(
            @RequestParam(name = "user_id") @Valid Long userId,
            @RequestParam(name = "question_id") @Valid Long questionId,
            @RequestParam(name = "answer_id") @Valid Long answerId,
            @RequestParam(name = "survey_id") @Valid Long surveyId) {
        customerService.response(userId, questionId, answerId, surveyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "Get result")
    public List<ResponseDto> getResult(@RequestParam(name = "userId") @NotNull Long userId) {
        return customerService.getResult(userId);
    }
}
