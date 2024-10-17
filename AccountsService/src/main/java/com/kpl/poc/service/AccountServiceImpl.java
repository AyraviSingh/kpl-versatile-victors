package com.kpl.poc.service;

import com.kpl.poc.entity.AccountStatus;
import com.kpl.poc.entity.AccountEntity;
import com.kpl.poc.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<AccountEntity> createNewAccount(AccountEntity entity) {
        log.info("Creating new Account. User name: {}", entity.getUserName());

        entity.setAccountStatus(AccountStatus.INACTIVE);
        return repository.upsert(entity)
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<AccountEntity> getAccountsByNumber(String accountNumber) {
        log.info("Fetching account for number: {}", accountNumber);

        return repository.getAccountByNumber(accountNumber)
                .onErrorResume(Mono::error);
    }

    @Override
    public Flux<AccountEntity> getAllAccounts() {
        log.info("Fetching all users");

        return repository.getAllAccounts()
                .onErrorResume(Flux::error);
    }

    @Override
    public Mono<AccountEntity> updateAccount(AccountEntity entity, String accountNumber) {
        log.info("Updating details for Account: {}", accountNumber);

        return repository.getAccountByNumber(accountNumber)
                .switchIfEmpty(Mono.error(new Throwable("Account not found for number: " + accountNumber)))
                .flatMap(exEntity -> {
                    BeanUtils.copyProperties(entity, exEntity);
                    return repository.upsert(exEntity);
                })
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<AccountEntity> removeAccountByNumber(String accountNumber) {
        log.info("Removing account for number: {}", accountNumber);

        return repository.removeAccountsByNumber(accountNumber)
                .onErrorResume(Mono::error);
    }
}
