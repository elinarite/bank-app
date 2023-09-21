package com.example.bankApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Value
public class ClientForManagerDto {

    Integer status;
    String taxCode;
    String firstName;
    String lastName;
    String email;
    String address;
    String phone;


}
