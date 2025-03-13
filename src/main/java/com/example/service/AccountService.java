package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
@Transactional
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public boolean hasUsername(String username){
        return accountRepository.findAccountByUsername(username) != null;
    }
    public Account saveAccount(Account account){
       return accountRepository.save(account);
    }

    public Account verifyAccount(Account account){
        return accountRepository.findAccountByUsernameAndPassword(
            account.getUsername(), account.getPassword());
    }

    public Account findAccountById(int id){
        return accountRepository.findAccountByAccountId(id);
    }
}
