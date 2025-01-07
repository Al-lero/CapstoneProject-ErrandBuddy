package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Admin;
import com.errandbuddy.errandbuddy.Dto.request.CreateAdminRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Repository.AdminRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import com.errandbuddy.errandbuddy.utils.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


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
                .email(createAdminRequest.getEmail())
                .name(createAdminRequest.getName())
                .password(createAdminRequest.getPassword())
                .phoneNumber(createAdminRequest.getPhoneNumber())
                .build();

        ErrandBuddyResponse response = adminService.createAdmin(createAdminRequest);

        assertEquals(ErrandBuddyUtils.Admin_Created_Successfully, response.getResponseMessage());
    }
    }




