package com.example.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long  id;
    private final String accountHolderName;
    private Double balance;
    private Long accountNumber;


}
