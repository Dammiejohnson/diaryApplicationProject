package com.technophile.diaryapp.services;

import com.technophile.diaryapp.dtos.requests.UpdateDiaryForm;
import com.technophile.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophile.diaryapp.models.Diary;
import com.technophile.diaryapp.models.Entry;
import com.technophile.diaryapp.models.User;
import com.technophile.diaryapp.repositories.DiaryRepository;
import com.technophile.diaryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Override
    public Diary createDiary(String title, String userId) {
        User user = userRepository.findUserById(userId)
                        .orElseThrow(() -> new DiaryAppApplicationException("User with id not found"));
        Diary diary = new Diary(title);
        Diary savedDiary = diaryRepository.save(diary);
        user.getDiaries().add(diary);
        userRepository.save(user);
        return savedDiary;
    }

    @Override
    public String updateDiary(String diaryId, UpdateDiaryForm updateDiaryForm) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() ->new DiaryAppApplicationException("Diary does not exist"));
        if(!updateDiaryForm.getDiaryTitle().trim().equals("") || updateDiaryForm.getDiaryTitle() == null){
        diary.setTitle(updateDiaryForm.getDiaryTitle());
        diaryRepository.save(diary);
        }
        return "Diary has been updated";
    }

    @Override
    public Diary addEntries(List<Entry> entries, String diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryAppApplicationException("Diary does not exist"));
        diary.getEntries().addAll(entries);
        return diaryRepository.save(diary);
    }

    @Override
    public Diary findDiary(String id) {
        return diaryRepository.findById(id).orElse(null);
    }
}
