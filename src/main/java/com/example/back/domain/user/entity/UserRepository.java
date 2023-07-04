package com.example.back.domain.user.entity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
