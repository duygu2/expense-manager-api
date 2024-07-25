package com.example.expensemanager.dto.transaction.requests;

import com.example.expensemanager.enums.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransactionRequest {
    private Double amount;

    private  String description;

    private LocalDateTime date;

    private Long userId;

    private TransactionCategory category;

    private LocalDateTime updatedAt;
}
