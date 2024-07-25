package com.example.expensemanager.model;

import com.example.expensemanager.enums.TimeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_logs")
public class TransactionLog {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private TimeType timeType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;


}
