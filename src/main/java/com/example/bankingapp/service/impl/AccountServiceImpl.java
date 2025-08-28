package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
   private AccountRepository accountRepository;

   public AccountServiceImpl(AccountRepository accountRepository)  {
       this.accountRepository = accountRepository;
   }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
       Account account = AccountMapper.mapToAccount(accountDto);
       Account savedAccount = accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not Found"));
        account.setBalance(account.getBalance()+amount);
            Account SavedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(SavedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not Found"));
        if(account.getBalance()<amount) {
            throw new RuntimeException("Insufficient Balance");
        }
             account.setBalance(account.getBalance()-amount);
            Account savedAccount = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(savedAccount);


    }

    @Override
    public List<AccountDto> getAllAccounts() {
       List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
                         .collect(Collectors.toList());


    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account not Found"));
        accountRepository.deleteById(id);
    }
}
