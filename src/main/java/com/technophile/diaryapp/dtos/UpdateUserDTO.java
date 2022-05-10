package com.technophile.diaryapp.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserDTO {
    private String email;
    private String password;
}
