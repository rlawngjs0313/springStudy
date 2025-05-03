package com.example.springstudy.domain.test.service.query;


import com.example.springstudy.domain.test.exception.handler.TestHandler;
import com.example.springstudy.global.apiPayload.code.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void CheckFlag(int flag) {
        if (flag == 1){
            throw new TestHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
