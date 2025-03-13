package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByUsername(String username);

    Account findAccountByUsernameAndPassword(String username, String password);

    Account findAccountByAccountId(int accountId);
}
