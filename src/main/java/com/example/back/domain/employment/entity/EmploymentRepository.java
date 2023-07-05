package com.example.back.domain.employment.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    List<Employment> findAllByUserId(Long userId);
}
