package com.finance.api.controller;

import com.finance.api.dto.AccountDTO;
import com.finance.api.model.Account;
import com.finance.api.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Contas Financeiras", description = "Gerenciamento de contas financeiras")
public class AccountController {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    
    @Autowired
    private AccountService accountService;
    
    @PostMapping
    @Operation(summary = "Criar nova conta", description = "Cria uma nova conta financeira")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        logger.info("Creating new account: {}", accountDTO.getName());
        Account account = accountService.createAccount(accountDTO);
        logger.info("Account created successfully with id: {}", account.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }
    
    @GetMapping
    @Operation(summary = "Listar contas", description = "Lista todas as contas financeiras")
    public ResponseEntity<List<Account>> getAllAccounts() {
        logger.info("Fetching all accounts");
        List<Account> accounts = accountService.getAllAccounts();
        logger.info("Found {} accounts", accounts.size());
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar conta por ID", description = "Busca uma conta financeira específica")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        logger.info("Fetching account with id: {}", id);
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar conta", description = "Atualiza uma conta existente")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDTO) {
        logger.info("Updating account with id: {}", id);
        Account updated = accountService.updateAccount(id, accountDTO);
        logger.info("Account updated successfully");
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar conta", description = "Remove uma conta financeira")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        logger.info("Deleting account with id: {}", id);
        accountService.deleteAccount(id);
        logger.info("Account deleted successfully");
        return ResponseEntity.noContent().build();
    }
}
