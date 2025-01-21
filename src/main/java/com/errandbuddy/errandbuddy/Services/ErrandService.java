package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Dto.request.CreateErrandRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;

public interface ErrandService {

    ErrandBuddyResponse createErrand(CreateErrandRequest createErrandRequest);
}
