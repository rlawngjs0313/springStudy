package com.example.springstudy.global.apiPayload.exception;

import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.code.ErrorStatus;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("{}: {}",e.getClass().getSimpleName() , e.getMessage());
        List<Map<String, String>> errors = new ArrayList<>();
        BaseErrorCode code = ErrorStatus.BAD_REQUEST;

        // 에러 매핑
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            Map<String, String> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
            errors.add(error);
        }

        ApiResponse<List<Map<String, String>>> response = ApiResponse.onFailure(
                code.getCode(),
                code.getMessage(),
                errors
        );
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationException(ConstraintViolationException e) {
        log.warn("ConstraintViolationException: {}", e.getMessage());
        BaseErrorCode code = ErrorStatus.BAD_REQUEST;
        List<Map<String, String>> errors = e.getConstraintViolations().stream()
                .map(violation -> {
                    Map<String, String> errorMap = new HashMap<>();
                    String propertyPath = violation.getPropertyPath().toString();
                    String field = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
                    errorMap.put(field, violation.getMessage());
                    return errorMap;
                })
                .toList();

        ApiResponse<List<Map<String, String>>> response = ApiResponse.onFailure(code.getCode(), code.getMessage(), errors);
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