package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Admin;
import com.errandbuddy.errandbuddy.Dto.request.CreateAdminRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Repository.AdminRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import com.errandbuddy.errandbuddy.utils.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class AdminServiceImplementationTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;
    private CreateAdminRequest adminRequest;


    @BeforeEach
    public void setup(){
        adminRequest = new CreateAdminRequest();
        adminRequest.setEmail("utieyionealero@yahoo.com");
        adminRequest.setName("Alero");
        adminRequest.setPassword("900012");
        adminRequest.setPhoneNumber("08120819973");
        adminRequest.setRoletype(RoleType.SUPER_ADMIN);

        adminRepository.deleteAll();


    }


    @Test
    public void testCreateAdmin_AdminExists(){
        CreateAdminRequest createAdminRequest = new CreateAdminRequest();
        createAdminRequest.setName("Alero");
        createAdminRequest.setPassword("900012");
        createAdminRequest.setPhoneNumber("08120819973");
        createAdminRequest.setRoletype(RoleType.SUPER_ADMIN);
        createAdminRequest.setEmail("utieyionealero@yahoo.com");


        Admin existingAdmin = Admin.builder()
                .email(createAdminRequest.getEmail()
                )
                .name(createAdminRequest.getName())
                .password(createAdminRequest.getPassword())
                .phoneNumber(createAdminRequest.getPhoneNumber())
                .build();
        adminRepository.save(existingAdmin);

        ErrandBuddyResponse response = adminService.createAdmin(createAdminRequest);

        assertEquals(ErrandBuddyUtils.Admin_Exist_Message, response.getResponseMessage());
    }

//    @Test
//    public void testThatAdminCanLogin(){
//        ErrandBuddyResponse response = adminService.createAdmin(CreateAdminRequest.builder().build());
//        assertEquals(ErrandBuddyUtils.Admin_Created_Successfully, response.getResponseMessage());
//
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("utieyionealero@yahoo.com");
//        loginRequest.setPassword("900012");
//
//        boolean loginSuccessful = adminService.loginAdmin(loginRequest);
//        assertNotNull(loginSuccessful);
//        assertEquals(true, loginSuccessful);
//
//    }

    @Test
    public void testThatAdminCanLogin() {

        CreateAdminRequest createAdminRequest = CreateAdminRequest.builder()
                .email("utieyionealero@yahoo.com")
                .name("Alero")
                .password("900012")
                .phoneNumber("08120819973")
                .roletype(RoleType.SUPER_ADMIN)
                .build();

        ErrandBuddyResponse response = adminService.createAdmin(createAdminRequest);
        assertEquals(ErrandBuddyUtils.Admin_Created_Successfully, response.getResponseMessage());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("utieyionealero@yahoo.com");
        loginRequest.setPassword("900012");

        boolean loginSuccessful = adminService.loginAdmin(loginRequest);

        assertNotNull(loginSuccessful);
        assertEquals(true, loginSuccessful);
    }


}




