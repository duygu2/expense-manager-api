package com.example.expensemanager.dto.transaction.responses;

import com.example.expensemanager.enums.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTransactionResponse {
    private Long id;

    private Double amount;

    private  String description;

    private LocalDateTime date;

    private Long userId;

    private TransactionCategory category;

}
