package com.errandbuddy.errandbuddy.Controller;

import com.errandbuddy.errandbuddy.Dto.request.CreateBuddyRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Services.BuddyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/buddy")
public class BuddyController {

    private final BuddyService buddyService;

    @PostMapping("create")
    public ErrandBuddyResponse createBuddy(@Valid @RequestBody CreateBuddyRequest createBuddyRequest){
        return buddyService.createBuddy(createBuddyRequest);
    }

    @PostMapping("login")
    public ErrandBuddyResponse loginBuddy(@Valid @RequestBody LoginRequest loginRequest){
        Boolean loginSuccessful = buddyService.loginBuddy(loginRequest);
        if(loginSuccessful) {
            return ErrandBuddyResponse.builder()
                    .responseMessage("Buddy Login Successfully")
                    .build();
        } else {
            return ErrandBuddyResponse.builder()
                    .responseMessage("Invalid details"
                    )
                    .build();
        }
    }
}
