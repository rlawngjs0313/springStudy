package com.example.springstudy.domain.test.controller;


import com.example.springstudy.domain.test.converter.TestConverter;
import com.example.springstudy.domain.test.dto.TestResDTO;
import com.example.springstudy.domain.test.service.query.TestQueryServiceImpl;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseCode;
import com.example.springstudy.global.apiPayload.code.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestQueryServiceImpl testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResDTO.TestDTO> test() {
        BaseCode code = SuccessStatus.OK;
        return ApiResponse.onSuccess(
                code.getCode(),
                code.getMessage(),
                TestConverter.toTestDTO("ThisIsTest")
        );
    }

    @GetMapping("/exception")
    public ApiResponse<TestResDTO.TestExceptionDTO> exception(@RequestParam int flag) {
        testQueryService.CheckFlag(flag);
        BaseCode code = SuccessStatus.OK;
        return ApiResponse.onSuccess(
                code.getCode(),
                code.getMessage(),
                TestConverter.toTestExceptionDTO(flag)
        );
    }
}
