package com.example.expensemanager.controller;

import com.example.expensemanager.dto.user.requests.CreateUserRequest;
import com.example.expensemanager.dto.user.requests.UpdateUserRequest;
import com.example.expensemanager.dto.user.response.CreateUserResponse;
import com.example.expensemanager.dto.user.response.GetAllUserResponse;
import com.example.expensemanager.dto.user.response.GetUserByIdResponse;
import com.example.expensemanager.dto.user.response.UpdateUserResponse;
import com.example.expensemanager.service.abstracts.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<GetAllUserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("getById/{id}")
    public Optional<GetUserByIdResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("update/{id}")
    public UpdateUserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(request,id);
    }
}
