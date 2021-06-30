package com.example.demo.controller.admin;

import com.example.demo.model.dto.SurveyDto;
import com.example.demo.service.ISurveyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/survey")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('write')")
public class SurveyController {

    private final ISurveyService surveyService;

    @PostMapping("/add/survey")
    @ApiOperation(value = "(Admin)Adding question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> addSurvey(@RequestBody @Valid SurveyDto surveyDto) {
        return ResponseEntity.ok(surveyService.addSurvey(surveyDto));
    }

    @DeleteMapping("/remove/{surveyId}")
    @ApiOperation(value = "(Admin)Remove question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> removeSurvey(
            @RequestParam @NotNull Long surveyId
    ) {
        return ResponseEntity.ok(surveyService.removeSurvey(surveyId));
    }

    @PutMapping("/update/{surveyId}")
    @ApiOperation(value = "(Admin)Update question",
            authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Long> updateSurvey(
            @RequestBody @NotNull SurveyDto surveyDto) {
        return ResponseEntity.ok(surveyService.updateSurvey(surveyDto));
    }
}
