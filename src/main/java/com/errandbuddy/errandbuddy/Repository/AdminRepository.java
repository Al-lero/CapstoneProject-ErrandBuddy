package com.errandbuddy.errandbuddy.Repository;

import com.errandbuddy.errandbuddy.Data.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
    boolean existsAdminByEmail(String email);
}
