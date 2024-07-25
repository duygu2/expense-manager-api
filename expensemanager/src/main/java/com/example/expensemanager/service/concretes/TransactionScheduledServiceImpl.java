package com.example.expensemanager.service.concretes;

import com.example.expensemanager.dto.user.response.GetAllUserResponse;
import com.example.expensemanager.enums.TimeType;
import com.example.expensemanager.model.TransactionLog;
import com.example.expensemanager.model.User;
import com.example.expensemanager.repository.TransactionLogRepository;
import com.example.expensemanager.service.abstracts.TransactionScheduledService;
import com.example.expensemanager.service.abstracts.TransactionService;
import com.example.expensemanager.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionScheduledServiceImpl implements TransactionScheduledService {

    private final TransactionService transactionService;
    private final UserService userService;
    private final TransactionLogRepository transactionLogRepository;

    @Override
    @Scheduled(cron = "0 0 0 * * *") //her gün gece yarısı çalışacak
    public void calculateTotalExpensesDaily() {
        List<GetAllUserResponse> users = userService.getAllUser();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);


        for (GetAllUserResponse userResponse : users) {
            BigDecimal total = transactionService.getTotalExpenseByDateRange(userResponse.getId(), today, yesterday);
            System.out.println("Daily expenses - User ID " + userResponse.getId() + " - Total Amount " + total);
            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setUserId(userResponse.getId());
            transactionLog.setAmount(total);
            transactionLog.setTimeType(TimeType.DAILY);
            transactionLog.setDescription("Daily expenses - User ID " + userResponse.getId() + " - Total Amount " + total);
            transactionLogRepository.save(transactionLog);

        }

    }

    @Override
    @Scheduled(cron = "0 0 0 * * 1")
    public void calculateTotalExpensesWeekly() {
        List<GetAllUserResponse> users = userService.getAllUser();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusWeeks(1);

        for (GetAllUserResponse userResponse : users) {
            BigDecimal total = transactionService.getTotalExpenseByDateRange(userResponse.getId(), today, yesterday);
            System.out.println("Weekly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);

            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setUserId(userResponse.getId());
            transactionLog.setAmount(total);
            transactionLog.setTimeType(TimeType.WEEKLY);
            transactionLog.setDescription("Weekly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);
            transactionLogRepository.save(transactionLog);
            System.out.println("Weekly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);

        }

    }

    @Override
    @Scheduled(cron = "0 0 0 1 * *")
    public void calculateTotalExpensesMonthly() {
        List<GetAllUserResponse> users = userService.getAllUser();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusMonths(1);

        for (GetAllUserResponse userResponse : users) {
            BigDecimal total = transactionService.getTotalExpenseByDateRange(userResponse.getId(), today, yesterday);
            System.out.println("Monthly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);

            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setUserId(userResponse.getId());
            transactionLog.setAmount(total);
            transactionLog.setTimeType(TimeType.MONTHLY);
            transactionLog.setDescription("Monthly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);
            transactionLogRepository.save(transactionLog);
            System.out.println("Monthly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);

        }


    }

    @Override
    @Scheduled(cron = "0 0 0 1 1 *") //her yılın 1. ayı
    public void calculateTotalExpensesYearly() {
        List<GetAllUserResponse> users = userService.getAllUser();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusYears(1);

        for (GetAllUserResponse userResponse : users) {
            BigDecimal total = transactionService.getTotalExpenseByDateRange(userResponse.getId(), today, yesterday);
            System.out.println("Yearly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);

            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setUserId(userResponse.getId());
            transactionLog.setAmount(total);
            transactionLog.setTimeType(TimeType.YEARLY);
            transactionLog.setDescription("Monthly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);
            transactionLogRepository.save(transactionLog);
            System.out.println("Yearly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);

        }

    }
    @Override
    @Scheduled(fixedRate = 50000)
    public void calculateTotalExpensesSecond() {

            List<GetAllUserResponse> users = userService.getAllUser();

            for (GetAllUserResponse userResponse : users) {
                BigDecimal total = transactionService.getTotalExpensesByUserId(userResponse.getId());
                System.out.println("Secondly expenses - User ID " + userResponse.getId() + " - Total Amount " + total);

        }
    }
}
