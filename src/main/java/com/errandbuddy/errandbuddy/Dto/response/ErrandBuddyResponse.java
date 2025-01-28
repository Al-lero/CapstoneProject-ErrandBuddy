package com.errandbuddy.errandbuddy.Dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ErrandBuddyResponse {
    private String responseMessage;
    private boolean success;
    private String message;


}
