package com.example.expensemanager.dto.transaction.requests;

import com.example.expensemanager.enums.TransactionCategory;
import com.example.expensemanager.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {

    private Double amount;

    private  String description;

    private LocalDateTime date;

    private Long userId;

    private TransactionCategory category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}