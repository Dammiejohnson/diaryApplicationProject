package com.technophile.diaryapp.services;

import com.technophile.diaryapp.dtos.UpdateUserDTO;
import com.technophile.diaryapp.dtos.responses.UserDTO;
import com.technophile.diaryapp.dtos.requests.CreateAccountRequest;
import com.technophile.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophile.diaryapp.mappers.UserMapper;
import com.technophile.diaryapp.mappers.UserMapperImpl;
import com.technophile.diaryapp.models.Diary;
import com.technophile.diaryapp.models.User;
import com.technophile.diaryapp.repositories.DiaryRepository;
import com.technophile.diaryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;
    private UserMapper userMapper = new UserMapperImpl();

//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository; // this is a constructor injection
//    }

    @Override
    public UserDTO createAccount(CreateAccountRequest accountRequestDTO) {
        Optional<User> optionalUser = userRepository.findUserByEmail(accountRequestDTO.getEmail());
        if(optionalUser.isPresent())
            throw  new DiaryAppApplicationException("User is already present");
        User user = new User();
        user.setEmail(accountRequestDTO.getEmail());
        user.setPassword(accountRequestDTO.getPassword());
        user.setDiaries(new HashSet<>());

        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);

    }

    @Override
    public UserDTO findUserById(String userId) {
        User user = userRepository.findUserById(userId).orElseThrow(( )-> new DiaryAppApplicationException("user with id does not exists"));
          return userMapper.userToUserDTO(user);
    }

//    @Override
//    public UserDTO findUserByEmail(String email) {
//        Optional<User> optionalUser = userRepository.findUserByEmail(email);
//        if(optionalUser.isPresent()){
//            userMapper.userToUserDTO(optionalUser.get());
//        }
//        return null;
//    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
      return optionalUser.map(user -> userMapper.userToUserDTO(user)).orElse(null);
    }


    @Override
    public String updateUser(String userId, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findUserById(userId).orElseThrow(( )-> new DiaryAppApplicationException("user account with id does not exists"));
        boolean isUpdate = false;

        if (!(updateUserDTO.getEmail()== null ||updateUserDTO.getEmail().trim().equals(""))){
            user.setEmail(updateUserDTO.getEmail());
            isUpdate = true;}

        if (!(updateUserDTO.getPassword()== null ||updateUserDTO.getPassword().trim().equals(""))){
            user.setPassword(updateUserDTO.getPassword());
            isUpdate = true;}

        if (isUpdate){
            userRepository.save(user);}
        return "User details updated successfully";
    }

    @Override
    public User findUserByIdInternal(String userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new DiaryAppApplicationException("user account with id does not exists"));
    }

    @Override
    public Diary addNewDiary(String userId, Diary diary) {
        User user = userRepository.findUserById(userId).orElseThrow(( )-> new DiaryAppApplicationException("user account with id does not exists"));
        Diary savedDiary = diaryRepository.save(diary);
        user.getDiaries().add(savedDiary);
        userRepository.save(user);
        return savedDiary;
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(( )-> new DiaryAppApplicationException("user account with email does not exists"));
        diaryRepository.deleteAll(user.getDiaries());
        userRepository.delete(user);
    }

}
