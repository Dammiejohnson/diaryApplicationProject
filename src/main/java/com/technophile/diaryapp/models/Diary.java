package com.technophile.diaryapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document
@NoArgsConstructor
@Getter
@Setter
public class Diary {
    @Id
    private String id;
    private String title;
    private LocalDateTime creationTime;
    private Set<Entry> entries;

    public Diary(String title) {
        this.title = title;
        this.creationTime = LocalDateTime.now();
        this.entries = new HashSet<>();

    }

    public Diary(String id, String title) {
        this.id = id;
        this.title = title;
        this.creationTime = LocalDateTime.now();
        this.entries = new HashSet<>();
    }



    @Override
    public String toString() {
        return "Diary{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
