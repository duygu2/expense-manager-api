package com.example.expensemanager.service.concretes;

import com.example.expensemanager.dto.transaction.requests.CreateTransactionRequest;
import com.example.expensemanager.dto.transaction.requests.UpdateTransactionRequest;
import com.example.expensemanager.dto.transaction.responses.CreateTransactionResponse;
import com.example.expensemanager.dto.transaction.responses.GetAllTransactionResponse;
import com.example.expensemanager.dto.transaction.responses.GetTransactionByIdResponse;
import com.example.expensemanager.dto.transaction.responses.UpdateTransactionResponse;
import com.example.expensemanager.dto.user.response.GetUserByIdResponse;
import com.example.expensemanager.exception.BussinessExcepiton;
import com.example.expensemanager.mapper.TransactionMapper;
import com.example.expensemanager.model.Transaction;
import com.example.expensemanager.model.User;
import com.example.expensemanager.repository.TransactionRepository;
import com.example.expensemanager.service.abstracts.TransactionService;
import com.example.expensemanager.service.abstracts.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    @Override
    @Transactional
    public CreateTransactionResponse createTransaction(Long userId, CreateTransactionRequest request) {
        Optional<GetUserByIdResponse> userResponse = userService.getUserById(userId);
        if (userResponse.isPresent()) {
            Transaction transaction = TransactionMapper.INSTANCE.createTransactionMapper(request);

            User user = new User();
            user.setId(userId);
            transaction.setUser(user);

            Transaction createdTransaction = transactionRepository.save(transaction);
            return new CreateTransactionResponse(
                    createdTransaction.getId(),
                    createdTransaction.getAmount(),
                    createdTransaction.getDescription(),
                    createdTransaction.getDate(),
                    createdTransaction.getUser().getId(),
                    createdTransaction.getCategory(),
                    createdTransaction.getCreatedAt(),
                    createdTransaction.getUpdatedAt()
            );
        }
        throw new BussinessExcepiton("User not found");
    }

    @Override
    @Transactional
    public UpdateTransactionResponse updateTransaction(Long userId, Long transactionId, UpdateTransactionRequest request) {
        Optional<GetUserByIdResponse> user = userService.getUserById(userId);
        if(!user.isPresent()){
            throw new RuntimeException("User not found");
        }
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (!transaction.isPresent()) {
            throw new RuntimeException("Transaction not found");
        }
        Transaction foundTransaction = transaction.get();
        if (!foundTransaction.getUser().getId().equals(userId)) {
            throw new RuntimeException("Transaction does not belong to the user");
        }
        Transaction updatedTransaction = TransactionMapper.INSTANCE.updateTransactionMapper(request, foundTransaction);
        Transaction savedTransaction = transactionRepository.save(updatedTransaction);
        return new UpdateTransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getAmount(),
                savedTransaction.getDescription(),
                savedTransaction.getDate(),
                savedTransaction.getUser().getId(),
                savedTransaction.getCategory(),
                savedTransaction.getCreatedAt(),
                savedTransaction.getUpdatedAt()
        );
    }

    @Override
    @Transactional
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<GetAllTransactionResponse> getAllTransaction(Long userId) {
        Optional<GetUserByIdResponse> userResponse = userService.getUserById(userId);
        if (!userResponse.isPresent()) {
            throw new BussinessExcepiton("User not found!");
        }

        List<Transaction> transactions = transactionRepository.findAll();
        return TransactionMapper.INSTANCE.transactionListMapper(transactions);
    }

    @Override
    public GetTransactionByIdResponse getTransactionById(Long userId, Long transactionId) {
        Optional<GetUserByIdResponse> userResponse = userService.getUserById(userId);
        if(!userResponse.isPresent()){
            throw new BussinessExcepiton("User not found!");

        }
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if(!transaction.isPresent()){
            throw new BussinessExcepiton("Transaction not found!");
        }
        return new GetTransactionByIdResponse(
                transaction.get().getId(),
                transaction.get().getAmount(),
                transaction.get().getDescription(),
                transaction.get().getDate(),
                transaction.get().getUser().getId(),
                transaction.get().getCategory(),
                transaction.get().getCreatedAt(),
                transaction.get().getUpdatedAt()
        );
    }

    @Override
    public BigDecimal getTotalExpensesByUserId(Long userId) {
        return transactionRepository.getSumTotalExpensesByUserId(userId);
    }

    @Override
    public BigDecimal getTotalExpenseByDate(Long userId, LocalDate date) {
        LocalDateTime startDay = date.atStartOfDay();
        LocalDateTime endDay = date.atTime(LocalTime.MAX);

        return transactionRepository.getSumTotalExpenseByDate(userId, startDay, endDay);
    }

    @Override
    public BigDecimal getTotalExpenseByDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startToStartDate = startDate.atStartOfDay();
        LocalDateTime endToEndDate = endDate.atTime(LocalTime.MAX);

        return transactionRepository.getSumTotalExpenseByDate(userId, startToStartDate, endToEndDate);

    }
}
