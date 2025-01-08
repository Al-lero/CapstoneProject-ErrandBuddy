package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Dto.request.CreateBuddyRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;

public interface BuddyService {

    ErrandBuddyResponse createBuddy(CreateBuddyRequest buddyRequest);
    Boolean loginBuddy(LoginRequest loginRequest);
}
