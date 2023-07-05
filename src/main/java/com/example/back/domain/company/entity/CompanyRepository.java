package com.example.back.domain.company.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByUserId(String userId);
    Optional<Company> findByUserId(String userId);
}
