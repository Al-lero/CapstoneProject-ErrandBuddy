package com.errandbuddy.errandbuddy.Data.Model;

import com.errandbuddy.errandbuddy.utils.DeliveryLocation;
import com.errandbuddy.errandbuddy.utils.Location;
import com.errandbuddy.errandbuddy.utils.PickUpLocation;
import com.errandbuddy.errandbuddy.utils.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Errand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long buddyId;

    @NotBlank(message = "Description is Mandatory")
    private String description;

    private PickUpLocation pickUpLocation;

    private DeliveryLocation deliveryLocation;

    private Status status;

    private Location location;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updated;

}
