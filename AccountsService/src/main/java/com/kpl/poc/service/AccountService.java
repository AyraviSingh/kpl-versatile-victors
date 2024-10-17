package com.kpl.poc.service;

import com.kpl.poc.entity.AccountEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<AccountEntity> createNewAccount(AccountEntity entity);

    Mono<AccountEntity> getAccountsByNumber(String accountNumber);

    Flux<AccountEntity> getAllAccounts();

    Mono<AccountEntity> updateAccount(AccountEntity entity, String accountNumber);

    Mono<AccountEntity> removeAccountByNumber(String accountNumber);
}
