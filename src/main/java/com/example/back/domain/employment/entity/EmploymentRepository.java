package com.example.back.domain.employment.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    List<Employment> findAllByUserId(Long userId);
    List<Employment> findAllByCompanyId(Long companyId);
    Optional<Employment> findByUserId(Long userId);

    boolean findByUserIdAndRecruitmentId(Long userId, Long recruitmentId);

    Optional<Employment> findByUserIdAndCompanyId(Long userId, Long companyId);
}
