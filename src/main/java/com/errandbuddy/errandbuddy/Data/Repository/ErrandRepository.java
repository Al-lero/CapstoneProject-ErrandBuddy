package com.errandbuddy.errandbuddy.Data.Repository;

import com.errandbuddy.errandbuddy.Data.Model.Errand;
import com.errandbuddy.errandbuddy.utils.DeliveryLocation;
import com.errandbuddy.errandbuddy.utils.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ErrandRepository extends JpaRepository<Errand, Long> {

    Errand findErrandByLocation(Location location);
    boolean existsErrandByDeliveryLocation(DeliveryLocation deliveryLocation);
    List<Errand> findErrandByDeliveryLocation(DeliveryLocation deliveryLocation);

}
