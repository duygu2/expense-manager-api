package com.example.expensemanager.repository;

import com.example.expensemanager.enums.TimeType;
import com.example.expensemanager.model.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long>
{

    List<TransactionLog> findByTimeType(TimeType timeType);
}
