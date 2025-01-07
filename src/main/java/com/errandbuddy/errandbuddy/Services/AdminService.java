package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Dto.request.CreateAdminRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;

public interface AdminService {
    ErrandBuddyResponse createAdmin(CreateAdminRequest adminRequest);
//    Boolean loginAdmin(LoginRequest loginRequest);

}
