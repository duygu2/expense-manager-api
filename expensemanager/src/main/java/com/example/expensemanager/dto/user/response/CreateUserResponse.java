package com.example.expensemanager.dto.user.response;

import com.example.expensemanager.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {

    private Long id;

    private String username;

    private String password;

    private String email;

    private UserRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
