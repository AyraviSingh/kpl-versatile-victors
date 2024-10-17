package com.kpl.poc.service;

import com.kpl.poc.entity.TransactionEntity;
import com.kpl.poc.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<TransactionEntity> createNewTransaction(TransactionEntity entity) {
        log.info("Creating new transaction for account number: {}", entity.getAccountNumber());

        return repository.upsert(entity)
                .onErrorResume(Mono::error);
    }
}
