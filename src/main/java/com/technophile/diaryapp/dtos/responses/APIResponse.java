package com.technophile.diaryapp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class APIResponse {
    private Object payload;
    private String message;
    private boolean isSuccessful;
}
