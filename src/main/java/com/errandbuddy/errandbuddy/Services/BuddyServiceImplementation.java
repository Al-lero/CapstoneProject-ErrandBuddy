package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Buddy;
import com.errandbuddy.errandbuddy.Dto.request.CreateBuddyRequest;
import com.errandbuddy.errandbuddy.Dto.request.EmailDetails;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Repository.BuddyRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuddyServiceImplementation implements BuddyService {

    @Autowired
    public BuddyRepository buddyRepository;

    @Autowired
    EmailService emailService;


    @Override
    public ErrandBuddyResponse createBuddy(CreateBuddyRequest buddyRequest) {
        if (buddyRepository.existsBuddiesByEmail(buddyRequest.getEmail())){
            return ErrandBuddyResponse.builder()
                    .responseMessage(ErrandBuddyUtils.Buddy_Exist_Message)
                    .build();
        }

        Buddy newBuddy = Buddy.builder()
                .name(buddyRequest.getName())
                .age(buddyRequest.getAge())
                .email(buddyRequest.getEmail())
                .address(buddyRequest.getAddress())
                .phoneNumber(buddyRequest.getPhoneNumber())
                .password(buddyRequest.getPassword())
                .nin(buddyRequest.getNin())
                .build();

        Buddy savedBuddy = buddyRepository.save(newBuddy);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedBuddy.getEmail())
                .subject("welcome!")
                .messageBody("Welcome to Errand Buddy, where running your errands are magical\" +\n" +
                        "                        \"\\nErrandBuddy Name: \" + savedBuddy.getName() + \" \" + \"\\nErrandBuddy Email:\" + savedBuddy.getEmail() + \" \" + \"\\nErrandBuddy Password:\" + savedBuddy.getPassword())")
                .build();

        emailService.sendEmailAlert(emailDetails);

        return ErrandBuddyResponse.builder()
                .responseMessage(ErrandBuddyUtils.Buddy_Created_Successfully)
                .build();
    }

    @Override
    public Boolean loginBuddy(LoginRequest loginRequest) {
        Buddy buddy = buddyRepository.findByEmail(loginRequest.getEmail());

        if ( buddy == null) {
            System.out.println("Buddy not found: " + loginRequest.getPassword());
            return false;
        }

        boolean isPasswordCorrect = loginRequest.getPassword().equals(buddy.getPassword());
        System.out.println("Password check result: " + isPasswordCorrect);

        return isPasswordCorrect;

    }
}
