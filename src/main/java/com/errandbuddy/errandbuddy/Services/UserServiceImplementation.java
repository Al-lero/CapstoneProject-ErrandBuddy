package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Errand;
import com.errandbuddy.errandbuddy.Data.Model.User;
import com.errandbuddy.errandbuddy.Dto.request.*;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Exception.UserNotFoundException;
import com.errandbuddy.errandbuddy.Data.Repository.ErrandRepository;
import com.errandbuddy.errandbuddy.Data.Repository.UserRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import com.errandbuddy.errandbuddy.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class
UserServiceImplementation implements UserService{

    private List<Errand> errands = new ArrayList<>();

    @Autowired
    public UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    ErrandRepository errandRepository;

    @Override
    public ErrandBuddyResponse createUser(CreateUserRequest userRequest) {
        if (userRepository.existsUserByEmail(userRequest.getEmail())){
            return ErrandBuddyResponse.builder()
                    .responseMessage(ErrandBuddyUtils.User_Exist_Message)
                    .build();

        }

        User newUser = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .age(userRequest.getAge())
                .address(userRequest.getAddress())
                .nin(userRequest.getNin())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();

        User savedUser = userRepository.save(newUser);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("welcome!")
                .messageBody("Welcome to Errand Buddy, where running your errands are magical\" +\n" +
                "                        \"\\nErrandBuddyUser Name: \" + savedUser.getName() + \" \" + \"\\nErrandBuddyUser Email:\" + savedUser.getEmail() + \" \" + \"\\nErrandBuddyUser Password:\" + savedUser.getPassword())")
                .build();

        emailService.sendEmailAlert(emailDetails);

        return ErrandBuddyResponse.builder()
                .responseMessage(ErrandBuddyUtils.User_Created_Successfully)
                .build();

    }

    @Override
    public Boolean loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if ( user == null ) {
            System.out.println("User not found: " + loginRequest.getEmail());
            return false;
        }

        boolean isPasswordCorrect = loginRequest.getPassword().equals(user.getPassword());
        System.out.println("Password check result: " + isPasswordCorrect);

        return isPasswordCorrect;
    }


    @Override
    public ErrandBuddyResponse addNewErrand(CreateErrandRequest createErrandRequest) {
        Errand errand = Errand.builder()
                .userId(createErrandRequest.getUserId())
                .description(createErrandRequest.getDescription())
                .pickUpLocation(createErrandRequest.getPickUpLocation())
                .deliveryLocation(createErrandRequest.getDeliveryLocation())
                .status(Status.PENDING)
                .build();

        errands.add(errand);

        User user = userRepository.findById(errand.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String messageBody = "Your errand consists of " + errand.getDescription();
        emailService.sendEmailAlert(user.getEmail(), "New Errand", messageBody);

        return ErrandBuddyResponse.builder()
                .responseMessage("Errand created successfully")
                .build();
    }

    @Override
    public boolean updateUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest) {
        User existingUser = userRepository.findByEmail(updateUserDetailsRequest.getEmail());

        if (existingUser == null) {
            throw new UserNotFoundException("User not Found");
        }

        existingUser.setNin(updateUserDetailsRequest.getNin());
        existingUser.setPhoneNumber(updateUserDetailsRequest.getPhoneNumber());
        existingUser.setAge(updateUserDetailsRequest.getAge());
        existingUser.setAddress(updateUserDetailsRequest.getAddress());
        existingUser.setSurname(updateUserDetailsRequest.getSurname());
        existingUser.setPassword(updateUserDetailsRequest.getPassword());

        userRepository.save(existingUser);
        return true;
    }

//    @Override
//    public ErrandBuddyResponse userCanCreateErrand(CreateErrandRequest createErrandRequest) {
//        User user = userRepository.findById(createErrandRequest.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
////        Errand errand = new Errand();
////        errand.setUser(user);
////        errand.setDescription(createErrandRequest.getDescription());
////        errand.setPickUpLocation(createErrandRequest.getPickUpLocation());
////        errand.setDeliveryLocation(createErrandRequest.getDeliveryLocation());
////        errand.setStatus(createErrandRequest.getStatus());
//
//        Errand errand = Errand.builder()
//                .user(user)
//                .userId(user.getId())
//                .description(createErrandRequest.getDescription())
//                .pickUpLocation(createErrandRequest.getPickUpLocation())
//                .deliveryLocation(createErrandRequest.getDeliveryLocation())
//                .status(createErrandRequest.getStatus())
//                .build();
//        errandRepository.save(errand);
//
//        ErrandBuddyResponse response = ErrandBuddyResponse.builder()
//                .success(true)
//                .message("Errand created successfully")
//                .build();
//        return response;
//
//
//    }


}
