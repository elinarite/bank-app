package com.example.bankApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientCreateDto {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String password;
}
