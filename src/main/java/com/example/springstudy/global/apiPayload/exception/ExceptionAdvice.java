package com.example.springstudy.global.apiPayload.exception;

import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.code.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalException(GeneralException e) {
        log.warn("{}: {}",e.getClass().getSimpleName(), e.getCode().getMessage());
        BaseErrorCode code = e.getCode();
        ApiResponse<String> response = ApiResponse.onFailure(code.getCode(), code.getMessage(), null);
        return ResponseEntity.status(code.getStatus()).body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> Exception(Exception e) {
        log.warn("Internal Server exception: {}", e.getMessage());
        BaseErrorCode code = ErrorStatus.INTERNAL_SERVER_ERROR;
        ApiResponse<String> response = ApiResponse.onFailure(code.getCode(), code.getMessage(), null);
        return ResponseEntity.status(code.getStatus()).body(response);
    }
}