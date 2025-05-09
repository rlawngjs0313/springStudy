package com.example.springstudy.global.apiPayload;


import com.example.springstudy.global.apiPayload.code.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"success", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("success")
    private final boolean isSuccess;
    @JsonProperty("code")
    private final String code;
    @JsonProperty("message")
    private final String message;
    @JsonProperty("result")
    private final T result;

    public static <T> ApiResponse<T> ok(){
        return ApiResponse.onSuccess(SuccessStatus.OK.getCode(), SuccessStatus.OK.getMessage(), null);
    }

    public static <T> ApiResponse<T> created(){
        return ApiResponse.onSuccess(SuccessStatus.CREATED.getCode(), SuccessStatus.CREATED.getMessage(), null);
    }

    public static <T> ApiResponse<T> onSuccess(String code, String message, T result){
            return new ApiResponse<>(true, code, message, result);
    }

    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T result){
        return new ApiResponse<>(false, code, message, result);
    }
}
