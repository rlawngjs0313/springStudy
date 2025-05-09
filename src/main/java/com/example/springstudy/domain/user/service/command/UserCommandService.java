package com.example.springstudy.domain.user.service.command;

import com.example.springstudy.domain.user.dto.request.UserReqDTO;

public interface UserCommandService {

    void signUp(UserReqDTO.SignUp dto);
}
