package com.example.demo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(GeneralTestApiException.class)
    public ResponseEntity<ExceptionInfo> handleException(GeneralTestApiException apiException) {
        var info = new ExceptionInfo();
        info.setInfo(apiException.getMessage());

        if (info.getInfo().equals(TestApiError.E403_FORBIDDEN.toString())) {
            return new ResponseEntity<>(info, HttpStatus.FORBIDDEN);
        } else if (info.getInfo().equals(TestApiError.E404_NOT_FOUND.toString())) {
            return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionInfo> handleException(Exception exception) {
        var info = new ExceptionInfo();
        info.setInfo(exception.getMessage());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }
}
