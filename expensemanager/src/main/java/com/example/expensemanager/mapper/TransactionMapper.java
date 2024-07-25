package com.example.expensemanager.mapper;

import com.example.expensemanager.dto.transaction.requests.CreateTransactionRequest;
import com.example.expensemanager.dto.transaction.requests.UpdateTransactionRequest;
import com.example.expensemanager.dto.transaction.responses.GetAllTransactionResponse;
import com.example.expensemanager.dto.transaction.responses.GetTransactionByIdResponse;
import com.example.expensemanager.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction createTransactionMapper(CreateTransactionRequest request);

    GetTransactionByIdResponse getTransactionByIdMapper(Transaction transaction);


    GetAllTransactionResponse getAllTransactionMapper(Transaction transaction);

    List<GetAllTransactionResponse> transactionListMapper(List<Transaction> transactions);

    // @Mapping(target = "id",source = "id")
    //  @Mapping(target = "user", ignore = true)
    Transaction updateTransactionMapper(UpdateTransactionRequest createTransactionRequest , @MappingTarget Transaction transaction);

}
