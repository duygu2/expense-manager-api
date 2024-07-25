package com.example.expensemanager.service.concretes;

import com.example.expensemanager.enums.TimeType;
import com.example.expensemanager.model.TransactionLog;
import com.example.expensemanager.repository.TransactionLogRepository;
import com.example.expensemanager.repository.TransactionRepository;
import com.example.expensemanager.service.abstracts.TransactionScheduledService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionLogServiceImpl {
    private TransactionScheduledService transactionScheduledService;
    private TransactionLogRepository transactionLogRepository;

    public List<TransactionLog> getAllTransactionsByType(TimeType timeType) {
        return transactionLogRepository.findByTimeType(timeType);
    }

}
