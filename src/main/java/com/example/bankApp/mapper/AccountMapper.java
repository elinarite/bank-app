package com.example.bankApp.mapper;

import com.example.bankApp.dto.AccountForClientDto;
import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "clientId.firstName", target = "clientFirstName")
    @Mapping(source = "clientId.lastName", target = "clientLastName")
    @Mapping(source = "managerId.firstName", target = "managerFirstName")
    @Mapping(source = "managerId.lastName", target = "managerLastName")
    AccountForClientDto toAccountForClientDto(Account account);

    @Mapping(source = "clientId.firstName", target = "clientFirstName")
    @Mapping(source = "clientId.lastName", target = "clientLastName")
    @Mapping(source = "managerId.firstName", target = "managerFirstName")
    @Mapping(source = "managerId.lastName", target = "managerLastName")
    AccountForManagerDto toAccountforManagerDto(Account account);
}
