package com.example.springstudy.global.apiPayload.exception;

import com.example.springstudy.domain.mission.exception.code.MissionErrorCode;
import com.example.springstudy.domain.shop.exception.code.ShopErrorCode;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.code.ErrorStatus;
import com.example.springstudy.global.validation.annotation.ExistShop;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

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
        // Exception 검증 : Exception message로 ErrorCode 유추
        BaseErrorCode code = ErrorStatus.BAD_REQUEST;
        String message = e.getConstraintViolations().iterator().next().getMessage();
        if (message.contains("상점")) {
            code = ShopErrorCode.NOT_FOUND;
        }
        if (message.contains("미션")){
            code = MissionErrorCode.NOT_BEFORE_PROGRESS;
        }
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