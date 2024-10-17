package com.kpl.poc.controller;

import com.kpl.poc.entity.UserEntity;
import com.kpl.poc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<UserEntity>> createUser(@RequestBody UserEntity entity) {
        return service.createNewUser(entity)
                .map(entity1 -> ResponseEntity.status(HttpStatus.CREATED).body(entity1));
    }

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<UserEntity>> getUSerById(@PathVariable String id) {
        return service.getUserById(id)
                .map(entity1 -> ResponseEntity.status(HttpStatus.OK).body(entity1));
    }

    @GetMapping
    public Flux<ResponseEntity<UserEntity>> getAllUsers() {
        return service.getAllUsers()
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body(entity));
    }

    @PutMapping(value = "/{id}")
    public Mono<ResponseEntity<UserEntity>> updateExistingUSer(@RequestBody UserEntity entity, @PathVariable String id) {
        return service.updateUser(entity, id)
                .map(entity1 -> ResponseEntity.status(HttpStatus.CREATED).body(entity1));
    }

    @DeleteMapping(value = "/{userId}")
    public Mono<ResponseEntity<String>> removeUserById(@PathVariable String userId) {
        return service.removeUserById(userId)
                .map(entity -> ResponseEntity.status(HttpStatus.OK).body("User details deleted"));
    }
}
