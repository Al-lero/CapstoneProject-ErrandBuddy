package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Buddy;
import com.errandbuddy.errandbuddy.Data.Model.Errand;
import com.errandbuddy.errandbuddy.Data.Model.User;
import com.errandbuddy.errandbuddy.Dto.request.CreateErrandRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Exception.BuddyNotFoundException;
import com.errandbuddy.errandbuddy.Exception.UserNotFoundException;
import com.errandbuddy.errandbuddy.Repository.BuddyRepository;
import com.errandbuddy.errandbuddy.Repository.ErrandRepository;
import com.errandbuddy.errandbuddy.Repository.UserRepository;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import com.errandbuddy.errandbuddy.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ErrandServiceImplementation implements ErrandService {

    private List<Errand> errands = new ArrayList<>();

    @Autowired
    private ErrandRepository errandRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuddyRepository buddyRepository;

    @Autowired
    EmailService emailService;

    @Override
    public ErrandBuddyResponse createErrand(CreateErrandRequest createErrandRequest) {
        if (errandRepository.existsErrandByDeliveryLocation(createErrandRequest.getDeliveryLocation())) {
            return ErrandBuddyResponse.builder()
                    .responseMessage(ErrandBuddyUtils.Errand_Exist_Message)
                    .build();

        }

        Errand newErrand = Errand.builder()
                .userId(createErrandRequest.getUserId())
                .description(createErrandRequest.getDescription())
                .pickUpLocation(createErrandRequest.getPickUpLocation())
                .deliveryLocation(createErrandRequest.getDeliveryLocation())
                .status(Status.PENDING)
                .build();

        Errand savedErrand = errandRepository.save(newErrand);
        User savedUser = userRepository.findById(newErrand.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        Buddy savedBuddy = buddyRepository.findById(newErrand.getBuddyId()).orElseThrow(() -> new BuddyNotFoundException("Buddy not found"));

        String messageBody = "Your errand consists of " + savedErrand.getDescription();
        emailService.sendEmailAlert(savedUser.getEmail(), "New Errand", messageBody);
        emailService.sendEmailAlert(savedBuddy.getEmail(), "New Errand", messageBody);
        return ErrandBuddyResponse.builder()
                .responseMessage("Errand created successfully")
                .build();

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
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String messageBody = "Your errand consists of " + errand.getDescription();
        emailService.sendEmailAlert(user.getEmail(), "New Errand", messageBody);

        return ErrandBuddyResponse.builder()
                .responseMessage("Errand created successfully")
                .build();
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
//
//        errandRepository.save(errand);
//
//        ErrandBuddyResponse response = ErrandBuddyResponse.builder()
//                .success(true)
//                .message("Errand created successfully")
//                .build();
//        return response;
//    }




    @Override
    public List<Errand> getAllErrands() {
        return new ArrayList<>(errands);
    }
}
