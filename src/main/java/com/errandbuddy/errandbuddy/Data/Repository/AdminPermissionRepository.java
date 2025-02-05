package com.errandbuddy.errandbuddy.Data.Repository;

import com.errandbuddy.errandbuddy.Data.Model.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPermissionRepository extends JpaRepository<AdminPermission, Long> {

    boolean existsAdminPermissionByAdminId(Long adminId);
    AdminPermission findByAdminId(Long adminId);
}