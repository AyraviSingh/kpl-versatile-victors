package com.kpl.poc.repository;

import com.kpl.poc.entity.TransactionType;
import com.kpl.poc.entity.TransactionEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
//    private final ReactiveMongoOperations reactiveMongoOperations;
//
//    public UserRepositoryImpl(ReactiveMongoOperations reactiveMongoOperations) {
//        this.reactiveMongoOperations = reactiveMongoOperations;
//    }

    @Override
    public Mono<TransactionEntity> upsert(TransactionEntity entity) {
//        Query query = new Query(Criteria.where("userId").is(entity.getUserId()));
//        Update update = new Update()
//                .set("userId", entity.getUserId())
//                .set("userName", entity.getUserName())
//                .set("phone", entity.getPhone())
//                .set("city", entity.getCity())
//                .set("accountStatus", entity.getAccountStatus())
//                .set("accountType", entity.getAccountType())
//                .set("accountNumber", entity.getAccountNumber());
//        FindAndModifyOptions options = new FindAndModifyOptions().upsert(true).returnNew(true);
//
//        return reactiveMongoOperations.findAndModify(query, update, options, UserEntity.class);
        return Mono.just(mockTransaction());
    }

    private TransactionEntity mockTransaction() {
        return TransactionEntity.builder()
                .accountNumber("12334567890")
                .transactionType(TransactionType.CREDIT)
                .transactionAmount(34534.33)
                .balance(20023045.23)
                .build();
    }
}
