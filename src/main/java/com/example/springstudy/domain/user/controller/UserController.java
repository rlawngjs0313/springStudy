package com.example.springstudy.domain.user.controller;

import com.example.springstudy.domain.user.dto.request.UserReqDTO;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.exception.code.UserSuccessCode;
import com.example.springstudy.domain.user.service.command.UserCommandServiceImpl;
import com.example.springstudy.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Transactional
public class UserController {

    private final UserCommandServiceImpl userCommandService;

    @PostMapping("/sign-up")
    public String signUp(
            @Valid @ModelAttribute UserReqDTO.SignUp dto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            userCommandService.signUp(dto);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.UserLogin> login(
            @Valid @RequestBody UserReqDTO.SignIn dto
    ) {
        UserResDTO.UserLogin userLogin = userCommandService.login(dto);
        UserSuccessCode code = UserSuccessCode.LOGIN;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), userLogin);
    }
}
