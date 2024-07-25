package com.example.expensemanager.service.abstracts;

import java.math.BigDecimal;

public interface TransactionScheduledService {

    void calculateTotalExpensesDaily();

    void calculateTotalExpensesWeekly();

    void calculateTotalExpensesMonthly();

    void calculateTotalExpensesYearly();

    void calculateTotalExpensesSecond();

}
