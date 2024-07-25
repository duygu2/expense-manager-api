package com.example.expensemanager.service.abstracts;

import com.example.expensemanager.dto.transaction.requests.CreateTransactionRequest;
import com.example.expensemanager.dto.transaction.requests.UpdateTransactionRequest;
import com.example.expensemanager.dto.transaction.responses.CreateTransactionResponse;
import com.example.expensemanager.dto.transaction.responses.GetAllTransactionResponse;
import com.example.expensemanager.dto.transaction.responses.GetTransactionByIdResponse;
import com.example.expensemanager.dto.transaction.responses.UpdateTransactionResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    CreateTransactionResponse createTransaction(Long userId,CreateTransactionRequest request);

    UpdateTransactionResponse updateTransaction(Long userId, Long transactionId, UpdateTransactionRequest request);

    void deleteTransaction(Long transactionId);

    List<GetAllTransactionResponse> getAllTransaction(Long userId);

    GetTransactionByIdResponse getTransactionById(Long userId, Long transactionId);

    BigDecimal getTotalExpensesByUserId(Long userId);

    BigDecimal getTotalExpenseByDate(Long userId, LocalDate date);

    BigDecimal getTotalExpenseByDateRange(Long userId, LocalDate startDate, LocalDate endDate);
}
