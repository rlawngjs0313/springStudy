package com.example.springstudy.global.auth.controller;

import com.example.springstudy.domain.user.dto.request.UserReqDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberViewController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(
            Model model
    ) {
        model.addAttribute("signUp", UserReqDTO.SignUp.builder().build());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
