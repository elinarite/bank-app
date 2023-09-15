package com.example.bankApp.mapper;

import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {


    ManagerForClientDto toManagerForClientDto(Manager manager);

}
