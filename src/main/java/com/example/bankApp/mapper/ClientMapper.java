package com.example.bankApp.mapper;

import com.example.bankApp.dto.ClientForManagerDto;
import com.example.bankApp.dto.ClientCreateDto;
import com.example.bankApp.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientForManagerDto toClientforManagerDto(Client client);
    ClientCreateDto toClientCreateDto(Client client);
    Client toClientEntity(ClientCreateDto clientCreateDto);

}
