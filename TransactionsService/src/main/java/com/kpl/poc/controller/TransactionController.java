package com.kpl.poc.controller;

import com.kpl.poc.entity.TransactionEntity;
import com.kpl.poc.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/transactions")
@RestController
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<TransactionEntity>> transact(@RequestBody TransactionEntity entity) {
        return service.createNewTransaction(entity)
                .map(entity1 -> ResponseEntity.status(HttpStatus.CREATED).body(entity1));
    }
}
