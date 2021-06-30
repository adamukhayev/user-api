package com.example.demo.controller.admin;

import com.example.demo.model.dto.QuestionDto;
import com.example.demo.service.IQuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/question")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('write')")
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping("/add/question")
    @ApiOperation(value = "(Admin)Adding question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> addQuestion(@RequestBody @Valid QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.addQuestion(questionDto));
    }

    @DeleteMapping("/remove/{questionId}")
    @ApiOperation(value = "(Admin)Remove question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> removeQuestion(
            @RequestParam @NotNull Long questionId
    ) {
        return ResponseEntity.ok(questionService.removeQuestion(questionId));
    }

    @PutMapping("/update/{questionId}")
    @ApiOperation(value = "(Admin)Update question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> updateQuestion(
            @RequestBody @Valid QuestionDto questionDto
    ) {
        return ResponseEntity.ok(questionService.updateQuestion(questionDto));
    }

    @PostMapping("/add/all/save")
    @ApiOperation(value = "(Admin)Adding question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Void> addAllQuestion(@RequestBody @Valid List<QuestionDto> questionDto) {
        questionService.addAllQuestionSave(questionDto);
        return ResponseEntity.ok().build();
    }

}
