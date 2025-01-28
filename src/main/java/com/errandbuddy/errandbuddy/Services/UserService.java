package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.User;
import com.errandbuddy.errandbuddy.Dto.request.CreateErrandRequest;
import com.errandbuddy.errandbuddy.Dto.request.CreateUserRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.request.UpdateUserDetailsRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;

public interface UserService {

    ErrandBuddyResponse createUser(CreateUserRequest userRequest);
    Boolean loginUser(LoginRequest loginRequest);

    ErrandBuddyResponse addNewErrand(CreateErrandRequest createErrandRequest);

    boolean updateUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest);

//    ErrandBuddyResponse userCanCreateErrand(CreateErrandRequest createErrandRequest);
}
