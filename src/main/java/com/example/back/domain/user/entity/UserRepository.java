package com.example.back.domain.user.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByPhoneNumberAndName(String phoneNumber, String name);
}
