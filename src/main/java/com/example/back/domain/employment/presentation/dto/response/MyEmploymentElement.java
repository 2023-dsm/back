package com.example.back.domain.employment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyEmploymentElement {
    private final String companyName;
    private final String workArea;
    private final String workName;
    private final boolean status;
}
