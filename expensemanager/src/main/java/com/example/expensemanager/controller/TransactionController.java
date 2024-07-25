package com.example.expensemanager.controller;

import com.example.expensemanager.dto.transaction.requests.CreateTransactionRequest;
import com.example.expensemanager.dto.transaction.requests.UpdateTransactionRequest;
import com.example.expensemanager.dto.transaction.responses.CreateTransactionResponse;
import com.example.expensemanager.dto.transaction.responses.GetAllTransactionResponse;
import com.example.expensemanager.dto.transaction.responses.GetTransactionByIdResponse;
import com.example.expensemanager.dto.transaction.responses.UpdateTransactionResponse;
import com.example.expensemanager.service.abstracts.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/{userId}/transactions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/all")
    private List<GetAllTransactionResponse> getAllTransaction(@PathVariable Long userId) {
        return transactionService.getAllTransaction(userId);
    }

    @GetMapping("/{transactionId}")
    public GetTransactionByIdResponse getTransactionById(@PathVariable Long userId, @PathVariable Long transactionId) {
        return transactionService.getTransactionById(userId, transactionId);
    }

    @PostMapping()
    public CreateTransactionResponse createTransaction(@PathVariable @Valid Long userId,
                                                       @RequestBody CreateTransactionRequest request) {
        return transactionService.createTransaction(userId, request);
    }

    @PutMapping("/{transactionId}")
    public UpdateTransactionResponse updateTransaction(@PathVariable @Valid Long userId,
                                                       @PathVariable Long transactionId,
                                                       @RequestBody UpdateTransactionRequest request) {
        return transactionService.updateTransaction(userId, transactionId, request);
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }

    @GetMapping("/total-expenses")
    public ResponseEntity<BigDecimal> getTotalExpensesByUserId(
            @PathVariable Long userId) {
        BigDecimal totalExpenses = transactionService.getTotalExpensesByUserId(userId);
        return ResponseEntity.ok(totalExpenses);
    }


}
