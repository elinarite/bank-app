package com.example.bankApp.mapper;

import com.example.bankApp.dto.AccountForClientDto;
import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.dto.AgreementForClientDto;
import com.example.bankApp.dto.AgreementForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
//    @Mapping(source = "clientId.firstName", target = "clientFirstName")
//    @Mapping(source = "clientId.lastName", target = "clientLastName")
//    @Mapping(source = "managerId.firstName", target = "managerFirstName")
//    @Mapping(source = "managerId.lastName", target = "managerLastName")
    AgreementForClientDto toAgreementForClientDto(Agreement agreement);

//    @Mapping(source = "clientId.firstName", target = "clientFirstName")
//    @Mapping(source = "clientId.lastName", target = "clientLastName")
//    @Mapping(source = "managerId.firstName", target = "managerFirstName")
//    @Mapping(source = "managerId.lastName", target = "managerLastName")
    AgreementForManagerDto toAgreementforManagerDto(Account account);
}
