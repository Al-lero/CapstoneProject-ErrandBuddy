package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.AdminPermission;
import com.errandbuddy.errandbuddy.Data.Repository.AdminPermissionRepository;
import com.errandbuddy.errandbuddy.utils.PermissionType;
import com.errandbuddy.errandbuddy.utils.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPermissionServiceImplementation implements AdminPermissionService {

    private final AdminPermissionRepository adminPermissionRepository;

    @Autowired
    public AdminPermissionServiceImplementation(AdminPermissionRepository adminPermissionRepository) {
        this.adminPermissionRepository = adminPermissionRepository;
    }

    @Override
    public void assignPermission(Long adminId, PermissionType permissionType, Resource resource) {
        AdminPermission adminPermission = AdminPermission.builder()
                .adminId(adminId)
                .permissionType(permissionType)
                .resource(resource)
                .build();
        adminPermissionRepository.save(adminPermission);
    }


    public void revokePermission(Long id) {

        adminPermissionRepository.deleteById(id);

    }


    public Boolean hasPermission(Long adminId, PermissionType permissionType, Resource resource) {
        return adminPermissionRepository.findAll().stream()
                .anyMatch(ap -> ap.getAdminId().equals(adminId)
                && ap.getPermissionType().equals(permissionType)
                && ap.getResource().equals(resource));
    }



}
