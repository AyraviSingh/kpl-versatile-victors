package com.kpl.poc.controller;

import com.kpl.poc.entity.AccountEntity;
import com.kpl.poc.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/accounts")
@RestController
public class AccountsController {
    private final AccountService service;

    public AccountsController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<AccountEntity>> createAccount(@RequestBody AccountEntity entity) {
        return service.createNewAccount(entity)
                .map(entity1 -> ResponseEntity.status(HttpStatus.CREATED).body(entity1));
    }

    @GetMapping(value = "/{number}")
    public Mono<ResponseEntity<AccountEntity>> getAccountByNumber(@PathVariable String number) {
        return service.getAccountsByNumber(number)
                .map(entity1 -> ResponseEntity.status(HttpStatus.OK).body(entity1));
    }

    @GetMapping
    public Flux<ResponseEntity<AccountEntity>> getAllAccounts() {
        return service.getAllAccounts()
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(entity));
    }

    @PutMapping(value = "/{number}")
    public Mono<ResponseEntity<AccountEntity>> updateExistingAccount(@RequestBody AccountEntity entity, @PathVariable String number) {
        return service.updateAccount(entity, number)
                .map(entity1 -> ResponseEntity.status(HttpStatus.CREATED).body(entity1));
    }

    @DeleteMapping(value = "/number")
    public Mono<ResponseEntity<String>> removeUAccountByNumber(@PathVariable String number) {
        return service.removeAccountByNumber(number)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body("User details deleted"));
    }
}
