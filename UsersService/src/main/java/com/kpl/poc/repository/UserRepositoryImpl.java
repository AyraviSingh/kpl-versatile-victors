package com.kpl.poc.repository;

import com.kpl.poc.entity.AccountStatus;
import com.kpl.poc.entity.AccountType;
import com.kpl.poc.entity.UserEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository{
//    private final ReactiveMongoOperations reactiveMongoOperations;
//
//    public UserRepositoryImpl(ReactiveMongoOperations reactiveMongoOperations) {
//        this.reactiveMongoOperations = reactiveMongoOperations;
//    }

    @Override
    public Mono<UserEntity> upsert(UserEntity entity) {
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
        return Mono.just(mockUserData());
    }

    @Override
    public Mono<UserEntity> getUserById(String userId) {
//        Query query = new Query(Criteria.where("userId").is(userId));
//
//        return reactiveMongoOperations.findOne(query, UserEntity.class);
        return Mono.just(mockUserData())
                .flatMap(userData -> {
                    userData.setUserId(userId);
                    return Mono.just(userData);
                });
    }

    @Override
    public Flux<UserEntity> getAllUsers() {
//        return reactiveMongoOperations.findAll(UserEntity.class);
        List<UserEntity> userList = new ArrayList<>();
        userList.add(mockUserData());
        userList.add(mockUserData());
        return Flux.fromIterable(userList);
    }

    @Override
    public Mono<UserEntity> removeUsersById(String userId) {
//        Query query = new Query(Criteria.where("userId").is(userId));
//
//        return reactiveMongoOperations.findAndRemove(query, UserEntity.class);
        return Mono.just(mockUserData());
    }

    private UserEntity mockUserData() {
        return UserEntity.builder()
                .userName("John Doe")
                .accountNumber("12334567890")
                .accountStatus(AccountStatus.ACTIVE)
                .userId(UUID.randomUUID().toString())
                .accountType(AccountType.SAVINGS)
                .city("Bangalore")
                .phone("9999999999")
                .build();
    }
}
