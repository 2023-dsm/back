package com.example.back.domain.recruitment.entity;

import com.example.back.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recruitment extends BaseEntity {

    @NotNull
    private String companyName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @NotNull
    private String workName;

    @NotNull
    private String workArea;

    @NotNull
    private String detailWorkArea;

    @NotNull
    private int number;

    @NotNull
    private String jobDescription;

    @NotNull
    private String weekWorkTime;

    @NotNull
    private String monthWorkTime;

    @NotNull
    private int money;

    @NotNull
    private String manager;

    @NotNull
    private LocalDate closeDate;
}
