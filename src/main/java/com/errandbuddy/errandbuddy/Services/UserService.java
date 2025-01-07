package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Dto.request.CreateUserRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;

public interface UserService {

    ErrandBuddyResponse createUser(CreateUserRequest userRequest);
    Boolean loginUser(LoginRequest loginRequest);
}
