package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.Errand;
import com.errandbuddy.errandbuddy.Dto.request.CreateErrandRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;

import java.util.List;

public interface ErrandService {

    ErrandBuddyResponse createErrand(CreateErrandRequest createErrandRequest);
    ErrandBuddyResponse addNewErrand(CreateErrandRequest createErrandRequest);
    List<Errand> getAllErrands();
}
