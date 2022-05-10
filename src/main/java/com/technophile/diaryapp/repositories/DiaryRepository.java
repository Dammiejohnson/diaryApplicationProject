package com.technophile.diaryapp.repositories;

import com.technophile.diaryapp.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary, String> {
}
