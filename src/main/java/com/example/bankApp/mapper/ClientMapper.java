package com.example.bankApp.mapper;

import com.example.bankApp.dto.ClientForManagerDto;
import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.entity.Client;
import com.example.bankApp.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientForManagerDto toClientforManagerDto(Client client);

}
