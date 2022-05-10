package com.technophile.diaryapp.dtos.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDiaryForm {
    private String diaryTitle;

    public UpdateDiaryForm(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }
}
