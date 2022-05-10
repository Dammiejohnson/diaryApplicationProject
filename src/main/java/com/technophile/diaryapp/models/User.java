package com.technophile.diaryapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private String id;
    @NotNull @NotNull
    private String email;
    @NotNull @NotBlank
    private String password;
    @DBRef
    private Set<Diary> diaries;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        diaries = new HashSet<>();
    }

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("UserId: %s\nEmail: %s", id, email);
    }
}

