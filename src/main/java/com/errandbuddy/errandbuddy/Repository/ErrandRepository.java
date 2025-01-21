package com.errandbuddy.errandbuddy.Repository;

import com.errandbuddy.errandbuddy.Data.Model.Errand;
import com.errandbuddy.errandbuddy.utils.DeliveryLocation;
import com.errandbuddy.errandbuddy.utils.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrandRepository extends JpaRepository<Errand, Long> {

    Errand findErrandByLocation(Location location);
    boolean existsErrandByDeliveryLocation(DeliveryLocation deliveryLocation);

}
