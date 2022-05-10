package com.technophile.diaryapp.services;

import com.technophile.diaryapp.dtos.UpdateUserDTO;
import com.technophile.diaryapp.dtos.requests.CreateAccountRequest;
import com.technophile.diaryapp.dtos.responses.UserDTO;
import com.technophile.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophile.diaryapp.models.Diary;
import com.technophile.diaryapp.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    //if @DataMongoTest is used instead of the @SpringBootTest, an instance of the userservice would be done because it wount find the bean of this
    //because springboottest already scans and rerun the server

    @Autowired
    private DiaryService diaryService;
    private CreateAccountRequest accountRequest;

    @BeforeEach
    void setUp(){
        accountRequest = CreateAccountRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();
    }

    @Test
    void testCreateAccount(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        assertThat(userDTO.getId()).isNotNull();
        assertThat(userDTO.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    @DisplayName("When you try to create account with an email that already exists in the database,"
   + "that it throws a diary Exception with the message: user account already exists")

    void testhatThrowsExceptionWhenEmailAlreadyExists(){
        userService.createAccount(accountRequest);
        CreateAccountRequest accountRequest = CreateAccountRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();
        assertThatThrownBy(() -> userService.createAccount(accountRequest)).isInstanceOf(DiaryAppApplicationException.class).hasMessage("User is already present");
        //assertThrows(DiaryAppApplicationException.class, () -> userService.createAccount(accountRequest));
    }

    @Test
    void testThatCanGetUserInfomation(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        UserDTO foundUser = userService.findUserById(userDTO.getId());
        assertThat(userDTO.getId()).isEqualTo(foundUser.getId());
    }

    @Test
    void testThatCanUpdateUserInformation(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO("", "newerPassword");
         String result = userService.updateUser(userDTO.getId(), updateUserDTO);;
         assertThat(result).isEqualTo("User details updated successfully");
         UserDTO foundUser = userService.findUserById(userDTO.getId());
         assertThat(foundUser.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void thatTestThrowsExceptionWhenUserIdNotFound(){
        userService.createAccount(accountRequest);
        String id = "null id";
        UpdateUserDTO updateUserDto = UpdateUserDTO.builder()
                .password("newpassword")
                .email("newtestemail.com").build();
        assertThatThrownBy(()->userService.updateUser(id, updateUserDto)).
                isInstanceOf(DiaryAppApplicationException.class).hasMessage("user account with id does not exists");
    }

    @Test
    void testThatCanAddDiaryToUser(){
        UserDTO userDTO = userService.createAccount(accountRequest);
        //User user = userService.findUserByIdInternal(userDTO.getId());
        String diaryTitle = "diary title";
        Diary diary = new Diary(diaryTitle);
        Diary savedDiary = userService.addNewDiary(userDTO.getId(), diary);
        //assertThat(savedDiary.getId()).isEqualTo(userDTO.getId());
        assertThat(savedDiary.getId()).isNotNull();
        assertThat(savedDiary.getTitle()).isEqualTo("diary title");
    }

    @Test
    void testThatCanDeleteAUserAndItsDiaries(){
       UserDTO userDTO = userService.createAccount(accountRequest);
       Diary diary = diaryService.createDiary("new diary", userDTO.getId());
       userService.addNewDiary(userDTO.getId(),diary);
       userService.deleteUserByEmail(userDTO.getEmail());
       assertThatThrownBy(() ->userService.findUserById(userDTO.getId())).isInstanceOf(DiaryAppApplicationException.class).hasMessage("user with id does not exists");
       Diary foundDiary = diaryService.findDiary(diary.getId());
       assertThat(foundDiary).isNull();

    }

    @AfterEach
    void tearDown() {
        if (userService.findUserByEmail("test@gmail.com") != null) {
            userService.deleteUserByEmail("test@gmail.com");
        }
    }

}