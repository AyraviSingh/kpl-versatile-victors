package com.kpl.poc.service;

import com.kpl.poc.entity.AccountStatus;
import com.kpl.poc.entity.UserEntity;
import com.kpl.poc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserEntity> createNewUser(UserEntity entity) {
        log.info("Creating new user. User name: {}", entity.getUserName());

        entity.setAccountStatus(AccountStatus.INACTIVE);
        return repository.upsert(entity)
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<UserEntity> getUserById(String userId) {
        log.info("Fetching user for id: {}", userId);

        return repository.getUserById(userId)
                .onErrorResume(Mono::error);
    }

    @Override
    public Flux<UserEntity> getAllUsers() {
        log.info("Fetching all users");

        return repository.getAllUsers()
                .onErrorResume(Flux::error);
    }

    @Override
    public Mono<UserEntity> updateUser(UserEntity entity, String userId) {
        log.info("Updating details for user: {}", entity.getUserName());

        return repository.getUserById(userId)
                .switchIfEmpty(Mono.error(new Throwable("User not found for id: " + userId)))
                .flatMap(exEntity -> {
                    BeanUtils.copyProperties(entity, exEntity);
                    return repository.upsert(exEntity);
                })
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<UserEntity> removeUserById(String userId) {
        log.info("Removing user for id: {}", userId);

        return repository.removeUsersById(userId)
                .onErrorResume(Mono::error);
    }
}
