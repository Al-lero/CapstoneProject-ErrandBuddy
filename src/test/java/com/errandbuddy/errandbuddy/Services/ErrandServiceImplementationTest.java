package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Dto.request.CreateErrandRequest;
import com.errandbuddy.errandbuddy.Dto.request.LoginRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Repository.ErrandRepository;
import com.errandbuddy.errandbuddy.utils.DeliveryLocation;
import com.errandbuddy.errandbuddy.utils.ErrandBuddyUtils;
import com.errandbuddy.errandbuddy.utils.PickUpLocation;
import com.errandbuddy.errandbuddy.utils.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ErrandServiceImplementationTest {

    @Autowired
    private BuddyService buddyService;

    @Autowired
    private ErrandRepository errandRepository;

    @Autowired
    private ErrandService errandService;

    @BeforeEach
    public void setup(){
        CreateErrandRequest createErrandRequest = new CreateErrandRequest();
        createErrandRequest.setUserId(20L);
        createErrandRequest.setDescription("tomatoes 200 naira, pepper 500 naira , rice( 3 derica) 3000 naira");
        createErrandRequest.setDeliveryLocation(DeliveryLocation.APAPA);
        createErrandRequest.setPickUpLocation(PickUpLocation.YABA);
        createErrandRequest.setStatus(Status.PENDING);
        errandRepository.deleteAll();
    }

    @Test
    public void testCreateErrand_ErrandExists(){
        CreateErrandRequest createErrandRequest = new CreateErrandRequest();
        createErrandRequest.setUserId(14L);
        createErrandRequest.setDescription("Bread and some other food");
        createErrandRequest.setDeliveryLocation(DeliveryLocation.SURULERE);
        createErrandRequest.setPickUpLocation(PickUpLocation.APAPA);



        ErrandBuddyResponse response = errandService.createErrand(createErrandRequest);

        assertEquals(ErrandBuddyUtils.Errand_Exist_Message,response.getResponseMessage());
    }

    @Test
    public void testThatBuddyCanGetAllTheErrands() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("richardarokyo@yahoo.com");
        loginRequest.setPassword("000231");
        boolean loginSuccessful = buddyService.loginBuddy(loginRequest);

    }



}