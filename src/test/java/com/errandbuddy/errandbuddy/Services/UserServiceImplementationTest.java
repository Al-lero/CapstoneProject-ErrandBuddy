package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Errand;
import com.errandbuddy.errandbuddy.Data.Model.User;
import com.errandbuddy.errandbuddy.Dto.request.CreateErrandRequest;
import com.errandbuddy.errandbuddy.Dto.request.CreateUserRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.request.UpdateUserDetailsRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.ErrandBuddyApplication;
import com.errandbuddy.errandbuddy.Repository.ErrandRepository;
import com.errandbuddy.errandbuddy.Repository.UserRepository;
import com.errandbuddy.errandbuddy.utils.DeliveryLocation;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import com.errandbuddy.errandbuddy.utils.PickUpLocation;
import com.errandbuddy.errandbuddy.utils.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class UserServiceImplementationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private CreateUserRequest userRequest;

    @Autowired
    private ErrandService errandService;

    @Autowired
    private ErrandRepository errandRepository;

    @BeforeEach
    public void setup() {
        userRequest = new CreateUserRequest();
        userRequest.setEmail("utieyionealero@yahoo.com");
        userRequest.setName("Alero");
        userRequest.setSurname("Richard");
        userRequest.setPassword("900014");
        userRequest.setPhoneNumber("08020819978");
        userRequest.setAge(60);
        userRequest.setNin("22113345599");
        userRequest.setAddress("No 10 iba, lagos state");

        userRepository.deleteAll();
;
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

        User savedUser = userRepository.findByEmail(createUserRequest.getEmail());
        assertNotNull(savedUser, "User should be saved in the repository");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(createUserRequest.getEmail());
        loginRequest.setPassword("900014");

        boolean loginSuccessful = userService.loginUser(loginRequest);
        assertNotNull(loginSuccessful, "Login result should not be null");
        assertTrue(loginSuccessful, "User should be able to login successfully");
    }


    @Test
    public void testThatUserCanUpdateDetails_UserExists(){

        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
        request.setEmail("utieyionealero@yahoo.com");
        request.setNin("22113345599");
        request.setPhoneNumber("08020819978");
        request.setAge(60);
        request.setAddress("123 Test Street");
        request.setSurname("Richard");
        request.setName("Alero");
        request.setPassword("900014");

        User existingUser = new User();
        existingUser.setEmail("utieyionealero@yahoo.com");

        existingUser.setNin("22113345599");
        existingUser.setPhoneNumber("08020819978");
        existingUser.setAge(59);
        existingUser.setAddress("No 10 iba, lagos state");
        existingUser.setSurname("Richard");
        existingUser.setName("Alero");
        existingUser.setPassword("900014");


        userRepository.save(existingUser);

        boolean result = userService.updateUserDetails(request);

        assertTrue(result);
        User savedUser = userRepository.findByEmail("utieyionealero@yahoo.com");
        assertNotNull(savedUser);
        assertEquals(request.getNin(), savedUser.getNin());
        assertEquals(request.getPhoneNumber(), savedUser.getPhoneNumber());
        assertEquals(request.getAge(), savedUser.getAge());
        assertEquals(request.getAddress(), savedUser.getAddress());
        assertEquals(request.getSurname(), savedUser.getSurname());
        assertEquals(request.getName(), savedUser.getName());
        assertEquals(request.getPassword(), savedUser.getPassword());
    }

//    @Test
//    public void testUpdateUserDetails_UserNotExists() {
//
//        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
//        request.setEmail("nonexistentuser@example.com");
//        request.setNin("123456789");
//        request.setPhoneNumber("07012345678");
//        request.setAge(45);
//        request.setAddress("800 Nonexistent St");
//        request.setSurname("Unknown");
//        request.setName("Ghost");
//        request.setPassword("invisiblePwd123");
//
//        boolean result = userService.updateUserDetails(request);
//
//
//        assertFalse(result);
//
//
//        User savedUser = userRepository.findByEmail("nonexistentuser@example.com");
//        assertNull(savedUser);
//    }

//    @Test
//    public void testUserCanCreateErrand(){
//
//        User user = new User();
//        user.setEmail("utieyionealero@yahoo.com");
//        userRepository.save(user);
//
//        CreateErrandRequest request = new CreateErrandRequest();
//        request.setUserId(user.getId());
//        request.setDescription("A bag of beans 90 thousand naira");
//        request.setPickUpLocation(PickUpLocation.SURULERE);
//        request.setDeliveryLocation(DeliveryLocation.MUSHIN);
//        request.setStatus(Status.PENDING);
//
//        ErrandBuddyResponse response = errandService.createErrand(request);
//
//        assertTrue(response.isSuccess());
//
//        List<Errand> errands = errandRepository.findErrandByDeliveryLocation(DeliveryLocation.MUSHIN);
//        assertNotNull(errands);
//        assertEquals(1, errands.size());
//
//        Errand savedErrand = errands.get(0);
//        assertEquals(request.getDescription(), savedErrand.getDescription());
//        assertEquals(request.getPickUpLocation(), savedErrand.getPickUpLocation());
//        assertEquals(request.getDeliveryLocation(), savedErrand.getDeliveryLocation());
//        assertEquals(request.getStatus(), savedErrand.getStatus());
//    }
}




