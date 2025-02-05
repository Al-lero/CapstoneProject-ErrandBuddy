package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Data.Model.AdminPermission;
import com.errandbuddy.errandbuddy.Dto.request.CreateAdminPermissionRequest;
import com.errandbuddy.errandbuddy.Data.Repository.AdminPermissionRepository;
import com.errandbuddy.errandbuddy.utils.PermissionType;
import com.errandbuddy.errandbuddy.utils.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminPermissionServiceImplementationTest {

    @Autowired
    private AdminPermissionRepository adminPermissionRepository;

    @Autowired
    private AdminPermissionService adminPermissionService;

    private CreateAdminPermissionRequest permissionRequest;

    @BeforeEach
    public void setup() {
        permissionRequest = new CreateAdminPermissionRequest();
        permissionRequest.setAdminId(20L);
        permissionRequest.setPermissionType(PermissionType.VIEW);
        permissionRequest.setResource(Resource.USER);

        adminPermissionRepository.deleteAll();

    }

    @Test
    public void testAssignPermission() {
        adminPermissionService.assignPermission(
                permissionRequest.getAdminId(),
                permissionRequest.getPermissionType(),
                permissionRequest.getResource()
        );
        assertTrue(adminPermissionService.hasPermission(
                permissionRequest.getAdminId(),
                permissionRequest.getPermissionType(),
                permissionRequest.getResource()
        ));
    }

    @Test
    public void testRevokePermission() {
        adminPermissionService.assignPermission(
                permissionRequest.getAdminId(),
                permissionRequest.getPermissionType(),
                permissionRequest.getResource()
        );

        AdminPermission savedPermission = adminPermissionRepository.findAll().get(0);
        adminPermissionService.revokePermission(savedPermission.getId());

        assertFalse(adminPermissionService.hasPermission(
                permissionRequest.getAdminId(),
                permissionRequest.getPermissionType(),
                permissionRequest.getResource()
        ));

    }

    @Test
    public void testPermissionExists() {
        adminPermissionService.assignPermission(
                permissionRequest.getAdminId(),
                permissionRequest.getPermissionType(),
                permissionRequest.getResource()
        );

        boolean exists = adminPermissionService.hasPermission(
                permissionRequest.getAdminId(),
                permissionRequest.getPermissionType(),
                permissionRequest.getResource()
        );

        assertTrue(exists);
    }

    @Test
    public void testPermissionDoesNotExist() {
        boolean exists = adminPermissionService.hasPermission( 2L,
                PermissionType.EDIT,
                Resource.ERRAND
        );

        assertFalse(exists);
    }
}