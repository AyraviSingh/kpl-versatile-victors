package com.kpl.poc.service;

import com.kpl.poc.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserEntity> createNewUser(UserEntity entity);

    Mono<UserEntity> getUserById(String userId);

    Flux<UserEntity> getAllUsers();

    Mono<UserEntity> updateUser(UserEntity entity, String userId);

    Mono<UserEntity> removeUserById(String userId);
}
