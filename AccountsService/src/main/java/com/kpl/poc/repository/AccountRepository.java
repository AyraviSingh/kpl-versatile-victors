package com.kpl.poc.repository;

import com.kpl.poc.entity.AccountEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Mono<AccountEntity> upsert(AccountEntity entity);


    Mono<AccountEntity> getAccountByNumber(String userId);

    Flux<AccountEntity> getAllAccounts();

    Mono<AccountEntity> removeAccountsByNumber(String userId);
}
