package com.example.back.domain.user.entity;

import com.example.back.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private int age;

    @NotNull
    private String sex;

    @NotNull
    private String address;

    private String introduce;

    private String career;

    public void writeResume(String address, String introduce, String career) {
        this.address = address;
        this.introduce = introduce;
        this.career = career;
    }
}
