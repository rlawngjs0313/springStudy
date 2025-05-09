package com.example.springstudy.domain.test.dto;

import lombok.Builder;

public class TestResDTO {

    @Builder
    public record TestDTO(String testString){}

    @Builder
    public record TestExceptionDTO(int flag){}
}
