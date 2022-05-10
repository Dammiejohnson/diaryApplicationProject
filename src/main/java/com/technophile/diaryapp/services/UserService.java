package com.technophile.diaryapp.services;

import com.technophile.diaryapp.dtos.UpdateUserDTO;
import com.technophile.diaryapp.dtos.responses.UserDTO;
import com.technophile.diaryapp.dtos.requests.CreateAccountRequest;
import com.technophile.diaryapp.models.Diary;
import com.technophile.diaryapp.models.User;

public interface UserService {
    UserDTO createAccount(CreateAccountRequest accountRequestDTO); //it is advisable to return String than return User because you would be returning the
    //object that contains the password
    UserDTO findUserById(String userId);
    UserDTO findUserByEmail(String email);

    String updateUser(String id, UpdateUserDTO updateUserDTO);
    User findUserByIdInternal(String userId);
    Diary addNewDiary(String userId, Diary diary);

    void deleteUserByEmail(String email);
}
