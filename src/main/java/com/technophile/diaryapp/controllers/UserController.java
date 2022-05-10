package com.technophile.diaryapp.controllers;

import com.technophile.diaryapp.dtos.requests.CreateAccountRequest;
import com.technophile.diaryapp.dtos.responses.APIResponse;
import com.technophile.diaryapp.exceptions.DiaryAppApplicationException;
import com.technophile.diaryapp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewUserAccount(@RequestBody CreateAccountRequest accountRequest){
        log.info("Hit endpoint!");
        try {
            APIResponse response = APIResponse.builder()
                    .payload(userService.createAccount(accountRequest))
                    .message("user created successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (DiaryAppApplicationException exception) {
            APIResponse response = APIResponse.builder()
                    .message(exception.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
