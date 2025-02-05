package com.errandbuddy.errandbuddy.Controller;

import com.errandbuddy.errandbuddy.Dto.request.CreateAdminRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/create")
    public ErrandBuddyResponse createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        return adminService.createAdmin(createAdminRequest);
    }

    @PostMapping("login")
    public ErrandBuddyResponse loginAdmin(@Valid @RequestBody LoginRequest loginRequest) {
        Boolean loginSuccessful = adminService.loginAdmin(loginRequest);
        if (loginSuccessful) {
            return  ErrandBuddyResponse.builder()
                    .responseMessage("Login Successful!")
                    .build();
        } else {
            return ErrandBuddyResponse.builder()
                    .responseMessage("Invalid Credentials")
                    .build();
        }
    }
}
