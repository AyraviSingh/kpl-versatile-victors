package com.kpl.poc.service;

import com.kpl.poc.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<TransactionEntity> createNewTransaction(TransactionEntity entity);
}
