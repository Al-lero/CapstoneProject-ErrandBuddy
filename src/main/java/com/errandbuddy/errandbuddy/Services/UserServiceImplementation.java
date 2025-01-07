package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.User;
import com.errandbuddy.errandbuddy.Dto.request.CreateUserRequest;
import com.errandbuddy.errandbuddy.Dto.request.EmailDetails;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Repository.UserRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    public UserRepository userRepository;

    @Autowired
    EmailService emailService;

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

//        return loginRequest.getPassword().equals(user.getPassword());
        return isPasswordCorrect;
    }
}
