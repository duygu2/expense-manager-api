package com.example.expensemanager.controller;

import com.example.expensemanager.dto.auth.requests.LoginRequest;
import com.example.expensemanager.dto.auth.requests.RegisterRequest;
import com.example.expensemanager.service.abstracts.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request){
        return authService.login(request);
    }
}
