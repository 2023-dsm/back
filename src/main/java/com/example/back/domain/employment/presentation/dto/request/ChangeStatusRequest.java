package com.example.back.domain.employment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeStatusRequest {
    private boolean status;
}
