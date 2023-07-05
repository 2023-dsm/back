package com.example.back.domain.recruitment.entity;

import com.example.back.domain.company.entity.Company;
import com.example.back.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
    private String closeDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    private Company company;
}
