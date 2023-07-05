package com.example.back.domain.employment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationElement {
    private final Long id;
    private final String name;
    private final String sex;
    private final int age;

}
