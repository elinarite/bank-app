package com.example.bankApp.mapper;

import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.dto.TransactionDto;
import com.example.bankApp.entity.Manager;
import com.example.bankApp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "debitAccountId.clientId.lastName", target = "debitAccountLastName")
    @Mapping(source = "debitAccountId.id", target = "debitAccountId")
    @Mapping(source = "creditAccountId.clientId.lastName", target = "creditAccountLastName")
    @Mapping(source = "creditAccountId.id", target = "creditAccountId")
    TransactionDto toTransactionDtoDto(Transaction transaction);
}