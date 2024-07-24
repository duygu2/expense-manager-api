package com.example.expensemanager.dto.user.response;

import com.example.expensemanager.enums.UserRole;
import com.example.expensemanager.model.Transaction;
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
public class GetUserByIdResponse {

    private Long id;

    private String username;

    private String password;

    private String email;

    private UserRole role;

    private List<Transaction> transaction;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
