package com.errandbuddy.errandbuddy.Data.Repository;

import com.errandbuddy.errandbuddy.Data.Model.Buddy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {

    Buddy findByEmail(String email);
    boolean existsBuddiesByEmail(String email);
}
