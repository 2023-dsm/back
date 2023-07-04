package com.example.back.domain.recruitment.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findByActivityTypeEquals(ActivityType activityType);
}
