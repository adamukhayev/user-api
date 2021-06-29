package com.example.demo.controller.admin;

import com.example.demo.model.dto.AnswerDto;
import com.example.demo.service.IAnswerService;
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
@RequestMapping("/admin/answer")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('write')")
public class AnswerController {

    private final IAnswerService answerService;

    @PostMapping("/add/answer")
    @ApiOperation(value = "Adding question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> addAnswer(@RequestBody @Valid AnswerDto answerDto) {
        return ResponseEntity.ok(answerService.addAnswer(answerDto));
    }

    @DeleteMapping("/remove/{answerId}")
    @ApiOperation(value = "Remove answer",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> removeAnswer(
            @RequestParam @NotNull Long answerId
    ) {
        return ResponseEntity.ok(answerService.removeAnswer(answerId));
    }

    @PutMapping("/update/{answerId}")
    @ApiOperation(value = "Update answer",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> updateAnswer(
            @RequestBody @Valid AnswerDto answerDto
    ) {
        return ResponseEntity.ok(answerService.updateAnswer(answerDto));
    }

    @PostMapping("/add/all/save")
    @ApiOperation(value = "Adding question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Void> addAllQuestion(@RequestBody @Valid List<AnswerDto> answerDto) {
        answerService.allSaveAnswer(answerDto);
        return ResponseEntity.ok().build();
    }
}
