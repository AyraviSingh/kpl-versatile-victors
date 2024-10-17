package com.kpl.poc.repository;

import com.kpl.poc.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository {
    Mono<TransactionEntity> upsert(TransactionEntity entity);
}
