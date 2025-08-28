package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account =new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getAccountNumber()
        );
        return account;
    }
public static AccountDto mapToAccountDto(Account account){
        AccountDto AccountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getAccountNumber()
        );
        return AccountDto;
}
}
