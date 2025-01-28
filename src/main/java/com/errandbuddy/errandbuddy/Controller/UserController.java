package com.errandbuddy.errandbuddy.Controller;

import com.errandbuddy.errandbuddy.Dto.request.CreateUserRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.request.UpdateUserDetailsRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class  UserController {

    @Autowired
    private  UserService userService;


    @PostMapping("create")
    public ErrandBuddyResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }

    @PostMapping("login")
    public ErrandBuddyResponse loginUser(@Valid @RequestBody LoginRequest loginRequest){
        Boolean loginSuccessful = userService.loginUser(loginRequest);
        if (loginSuccessful) {
            return ErrandBuddyResponse.builder()
                    .responseMessage("User Login Successfully")
                    .build();
        } else {
            return ErrandBuddyResponse.builder()
                    .responseMessage("Invalid details")
                    .build();
        }
    }

    @PutMapping("/update")
    public boolean updateUserDetails(@Valid @RequestBody UpdateUserDetailsRequest updateUserDetailsRequest) {
        return userService.updateUserDetails(updateUserDetailsRequest);
    }
}
