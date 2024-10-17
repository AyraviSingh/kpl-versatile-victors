package com.kpl.poc.repository;

import com.kpl.poc.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<UserEntity> upsert(UserEntity entity);

    Mono<UserEntity> getUserById(String userId);

    Flux<UserEntity> getAllUsers();

    Mono<UserEntity> removeUsersById(String userId);
}
