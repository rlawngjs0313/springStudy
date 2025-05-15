package com.example.springstudy.domain.test.converter;

import com.example.springstudy.domain.test.dto.TestResDTO;

public class TestConverter {

    // String -> TestReqDTO
    public static TestResDTO.TestDTO toTestDTO(String testString){
        return TestResDTO.TestDTO.builder()
                .testString(testString)
                .build();
    }

    // flag -> ExceptionDTO
    public static TestResDTO.TestExceptionDTO toTestExceptionDTO(int flag){
        return TestResDTO.TestExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
