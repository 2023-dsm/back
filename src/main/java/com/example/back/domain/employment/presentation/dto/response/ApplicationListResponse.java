package com.example.back.domain.employment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationListResponse {
    List<ApplicationElement> applicationList;
}
