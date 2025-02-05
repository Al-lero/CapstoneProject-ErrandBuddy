package com.errandbuddy.errandbuddy.Dto.request;

import com.errandbuddy.errandbuddy.utils.DeliveryLocation;
import com.errandbuddy.errandbuddy.utils.PickUpLocation;
import com.errandbuddy.errandbuddy.utils.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateErrandRequest {

    private  Long userId;
    @NotBlank(message = "Description is Mandatory")
    private String description;

    private Long buddyId;

    private PickUpLocation pickUpLocation;

    private DeliveryLocation deliveryLocation;

    private Status status;

}
