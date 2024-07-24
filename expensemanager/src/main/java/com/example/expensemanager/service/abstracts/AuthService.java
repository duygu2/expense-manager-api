package com.example.expensemanager.service.abstracts;

import com.example.expensemanager.dto.auth.requests.LoginRequest;
import com.example.expensemanager.dto.auth.requests.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}
