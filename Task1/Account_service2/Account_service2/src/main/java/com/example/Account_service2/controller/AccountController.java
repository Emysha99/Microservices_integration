package com.example.Account_service2.controller;

import org.springframework.web.bind.annotation.*;
import com.example.Account_service2.dto.UserRequest;
import com.example.Account_service2.dto.UserResponse;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class AccountController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody UserResponse createUser(@RequestBody UserRequest userRequest){

        System.out.println("User details :" + userRequest);

        UserResponse userResponse=new UserResponse();
        userResponse.setUserID(UUID.randomUUID().toString());
        userResponse.setMessage("Successfully create the user");

        return userResponse;
    }
}
