package com.technophile.diaryapp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.technophile.diaryapp.config.EntryDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = EntryDeserializer.class)
public class Entry {
    private String id;
    private String text;
    private LocalDateTime entryTime;

    public Entry(String text ) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.entryTime = entryTime;
    }
}
