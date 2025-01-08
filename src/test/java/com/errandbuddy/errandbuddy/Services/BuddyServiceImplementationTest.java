package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Buddy;
import com.errandbuddy.errandbuddy.Dto.request.CreateBuddyRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Repository.BuddyRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuddyServiceImplementationTest {

    @Autowired
    private BuddyRepository buddyRepository;

    @Autowired
    private BuddyService buddyService;

    private CreateBuddyRequest buddyRequest;

    @BeforeEach
    public void setup(){
        buddyRequest = new CreateBuddyRequest();
        buddyRequest.setName("okocha");
        buddyRequest.setEmail("richardarokyo@yahoo.com");
        buddyRequest.setPassword("000231");
        buddyRequest.setAddress("23, ihala street. ojoku. lagos");
        buddyRequest.setPhoneNumber("09812345215");
        buddyRequest.setAge(19);
        buddyRequest.setNin("22234345641");

        buddyRepository.deleteAll();
    }

    @Test
    public void testCreateBuddy_BuddyExists(){

        Buddy existingBuddy = Buddy.builder()
                .name(buddyRequest.getName())
                .nin(buddyRequest.getNin())
                .password(buddyRequest.getPassword())
                .phoneNumber(buddyRequest.getPhoneNumber())
                .address(buddyRequest.getAddress())
                .email(buddyRequest.getEmail())
                .age(buddyRequest.getAge())
                .build();

        buddyRepository.save(existingBuddy);

        ErrandBuddyResponse response= buddyService.createBuddy(buddyRequest);

        assertEquals(ErrandBuddyUtils.Buddy_Exist_Message, response.getResponseMessage());
    }

    @Test
    public void testThatBuddyCanLogin(){

        CreateBuddyRequest createBuddyRequest = CreateBuddyRequest.builder()
                .name(buddyRequest.getName())
                .address(buddyRequest.getAddress())
                .nin(buddyRequest.getNin())
                .phoneNumber(buddyRequest.getPhoneNumber())
                .password(buddyRequest.getPassword())
                .phoneNumber(buddyRequest.getPhoneNumber())
                .age(buddyRequest.getAge())
                .email(buddyRequest.getEmail())
                .build();

        ErrandBuddyResponse response = buddyService.createBuddy(createBuddyRequest);
        assertEquals(ErrandBuddyUtils.Buddy_Created_Successfully, response.getResponseMessage());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("richardarokyo@yahoo.com");
        loginRequest.setPassword("000231");

        boolean loginSuccessful = buddyService.loginBuddy(loginRequest);

        assertNotNull(loginSuccessful);
        assertEquals(true, loginSuccessful);
    }

}