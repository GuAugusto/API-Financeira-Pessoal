package com.finance.api.service;

import com.finance.api.dto.AccountDTO;
import com.finance.api.model.Account;
import com.finance.api.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Transactional
    public Account createAccount(AccountDTO dto) {
        logger.info("Creating account with name: {}", dto.getName());
        Account account = new Account();
        account.setName(dto.getName());
        account.setBalance(dto.getBalance());
        account.setCreatedAt(LocalDateTime.now());
        Account saved = accountRepository.save(account);
        logger.info("Account created with id: {}", saved.getId());
        return saved;
    }
    
    public List<Account> getAllAccounts() {
        logger.info("Fetching all accounts from database");
        return accountRepository.findAll();
    }
    
    public Optional<Account> getAccountById(Long id) {
        logger.info("Fetching account by id: {}", id);
        return accountRepository.findById(id);
    }
    
    @Transactional
    public Account updateAccount(Long id, AccountDTO dto) {
        logger.info("Updating account with id: {}", id);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        account.setName(dto.getName());
        account.setBalance(dto.getBalance());
        Account updated = accountRepository.save(account);
        logger.info("Account updated successfully");
        return updated;
    }
    
    @Transactional
    public void deleteAccount(Long id) {
        logger.info("Deleting account with id: {}", id);
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        accountRepository.deleteById(id);
        logger.info("Account deleted successfully");
    }
}
