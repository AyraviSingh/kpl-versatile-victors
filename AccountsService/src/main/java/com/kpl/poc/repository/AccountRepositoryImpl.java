package com.kpl.poc.repository;

import com.kpl.poc.entity.AccountStatus;
import com.kpl.poc.entity.AccountType;
import com.kpl.poc.entity.AccountEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
//    private final ReactiveMongoOperations reactiveMongoOperations;
//
//    public UserRepositoryImpl(ReactiveMongoOperations reactiveMongoOperations) {
//        this.reactiveMongoOperations = reactiveMongoOperations;
//    }

    @Override
    public Mono<AccountEntity> upsert(AccountEntity entity) {
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
        return Mono.just(mockAccountDetails());
    }

    @Override
    public Mono<AccountEntity> getAccountByNumber(String userId) {
//        Query query = new Query(Criteria.where("userId").is(userId));
//
//        return reactiveMongoOperations.findOne(query, UserEntity.class);
        return Mono.just(mockAccountDetails())
                .flatMap(Mono::just);
    }

    @Override
    public Flux<AccountEntity> getAllAccounts() {
//        return reactiveMongoOperations.findAll(UserEntity.class);
        List<AccountEntity> accountEntities = new ArrayList<>();
        accountEntities.add(mockAccountDetails());
        accountEntities.add(mockAccountDetails());
        return Flux.fromIterable(accountEntities);
    }

    @Override
    public Mono<AccountEntity> removeAccountsByNumber(String userId) {
//        Query query = new Query(Criteria.where("userId").is(userId));
//
//        return reactiveMongoOperations.findAndRemove(query, UserEntity.class);
        return Mono.just(mockAccountDetails());
    }

    private AccountEntity mockAccountDetails() {
        return AccountEntity.builder()
                .userName("John Doe")
                .accountNumber("12334567890")
                .accountStatus(AccountStatus.ACTIVE)
                .accountType(AccountType.SAVINGS)
                .balance(20023045.23)
                .build();
    }
}
