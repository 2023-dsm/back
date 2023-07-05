package com.example.back.domain.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long userId);
    Optional<User> findByPhoneNumberAndName(String phoneNumber, String name);
}
