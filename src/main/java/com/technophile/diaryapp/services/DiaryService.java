package com.technophile.diaryapp.services;

import com.technophile.diaryapp.dtos.requests.UpdateDiaryForm;
import com.technophile.diaryapp.models.Diary;
import com.technophile.diaryapp.models.Entry;

import java.util.List;

public interface DiaryService {
    Diary createDiary(String title, String userId);
    String updateDiary(String diaryId, UpdateDiaryForm updateDiaryForm);
    Diary addEntries (List<Entry> entries, String diaryId);

    Diary findDiary(String id);
}
