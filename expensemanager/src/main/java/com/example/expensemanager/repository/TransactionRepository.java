package com.example.expensemanager.repository;

import com.example.expensemanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT SUM(amount) FROM Transaction where user.id= :userId")
    BigDecimal getSumTotalExpensesByUserId(Long userId);
    @Query("SELECT SUM(amount) FROM Transaction WHERE user.id =:userId AND date>= :startDate AND date<= :endDate")
    BigDecimal getSumTotalExpenseByDate(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
