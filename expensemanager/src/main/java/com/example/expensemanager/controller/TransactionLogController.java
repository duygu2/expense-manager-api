package com.example.expensemanager.controller;


import com.example.expensemanager.enums.TimeType;
import com.example.expensemanager.model.TransactionLog;
import com.example.expensemanager.service.abstracts.TransactionScheduledService;
import com.example.expensemanager.service.concretes.TransactionLogServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TransactionLogController {
    private final TransactionScheduledService transactionScheduledService;

    private final TransactionLogServiceImpl transactionLogService;

    @GetMapping
    public List<TransactionLog> getTransactionsByType(
            @RequestParam TimeType timeType) {
        return transactionLogService.getAllTransactionsByType(timeType);
    }
}
