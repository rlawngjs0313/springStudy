package com.example.springstudy.global.auth.exception;

import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.code.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        UserErrorCode code = UserErrorCode.UNAUTH;
        response.setContentType("application/json");
        response.setStatus(code.getStatus().value());
        ObjectMapper mapper = new ObjectMapper();

        ApiResponse<Object> errorResponse = ApiResponse.onFailure(
                code.getCode(),
                code.getMessage(),
                null
        );

        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
