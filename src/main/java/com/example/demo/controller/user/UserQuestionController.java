package com.example.demo.controller.user;

import com.example.demo.model.dto.SurveyDto;
import com.example.demo.service.IUserQuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@AllArgsConstructor
@RequestMapping("/user/question")
public class UserQuestionController {

    private final IUserQuestionService userQuestionService;

    @GetMapping
    @ApiOperation(value = "Get question")
    public ResponseEntity<Page<SurveyDto>> getAllQuestion(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(userQuestionService.getAllQuestion(pageable));
    }
}
