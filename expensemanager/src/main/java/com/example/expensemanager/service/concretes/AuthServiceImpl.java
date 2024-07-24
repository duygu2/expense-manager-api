package com.example.expensemanager.service.concretes;


import com.example.expensemanager.dto.auth.requests.LoginRequest;
import com.example.expensemanager.dto.auth.requests.RegisterRequest;
import com.example.expensemanager.model.User;
import com.example.expensemanager.security.JwtService;
import com.example.expensemanager.service.abstracts.AuthService;
import com.example.expensemanager.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    @Override
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setRole(registerRequest.getRole());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userService.saverUser(user);   }

    @Override
    public String login(LoginRequest loginRequest) {

        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        if(!authentication.isAuthenticated()){
            throw new RuntimeException("Gecersiz kullanıcı adı ya da şifre");
        }
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

        Map<String,Object> extraClaims = new HashMap<>();
        Set<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toSet());
        extraClaims.put("roles", roles);
        return jwtService.generateToken(loginRequest.getUsername(),extraClaims
        );
    }
}