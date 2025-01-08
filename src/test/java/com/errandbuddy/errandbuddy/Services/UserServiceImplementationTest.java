package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.User;
import com.errandbuddy.errandbuddy.Dto.request.CreateUserRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.ErrandBuddyApplication;
import com.errandbuddy.errandbuddy.Repository.UserRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplementationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private CreateUserRequest userRequest;

    @BeforeEach
    public void setup() {
        userRequest = new CreateUserRequest();
        userRequest.setEmail("kemiyione@ymail.com");
        userRequest.setName("Alero");
        userRequest.setSurname("Arokoyo");
        userRequest.setPassword("900012");
        userRequest.setPhoneNumber("08120819972");
        userRequest.setAge(21);
        userRequest.setNin("22113345566");
        userRequest.setAddress("No 30 ojo, lagos state");

        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser_UserExists() {

        User existingUser = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .nin(userRequest.getNin())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .age(userRequest.getAge())
                .build();

        userRepository.save(existingUser);

        ErrandBuddyResponse response = userService.createUser(userRequest);

        assertEquals(ErrandBuddyUtils.User_Exist_Message, response.getResponseMessage());
    }

    @Test
    public void testThatUserCanLogin(){

        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .nin(userRequest.getNin())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .age(userRequest.getAge())
                .build();

        ErrandBuddyResponse response = userService.createUser(createUserRequest);
        assertEquals(ErrandBuddyUtils.User_Created_Successfully, response.getResponseMessage());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("kemiyione@ymail.com");
        loginRequest.setPassword("900012");

        boolean loginSuccessful = userService.loginUser(loginRequest);

        assertNotNull(loginSuccessful);
        assertEquals(true, loginSuccessful);
    }
}
