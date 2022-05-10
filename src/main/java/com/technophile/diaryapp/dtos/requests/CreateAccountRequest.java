package com.technophile.diaryapp.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    private String email;
    private String password;
}
