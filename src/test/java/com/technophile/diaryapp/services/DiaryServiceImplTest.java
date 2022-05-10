package com.technophile.diaryapp.services;


import com.technophile.diaryapp.models.Diary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class DiaryServiceImplTest {
    @Autowired
    private DiaryService diaryService;

//    @Test
//    void testthatCancreateANewDiary(){
//        String title = new String("New diary Title");
//        String id = new String("626ba2e6f59c2b3cfb954046");
//        Diary diary = diaryService.createDiary(title,id);
//        assertThat(diary.getTitle()).isEqualTo("New diary Title");
//        assertThat(diary.getOwner().getId()).isEqualTo("626ba2e6f59c2b3cfb954046");
//    }

}
