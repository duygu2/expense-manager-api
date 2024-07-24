package com.example.expensemanager.dto.user.requests;

import com.example.expensemanager.enums.UserRole;
import com.example.expensemanager.model.Transaction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    private UserRole role;

    private List<Transaction> transaction;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
