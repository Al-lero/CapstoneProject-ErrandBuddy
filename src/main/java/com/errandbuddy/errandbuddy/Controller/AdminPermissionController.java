package com.errandbuddy.errandbuddy.Controller;

import com.errandbuddy.errandbuddy.Dto.request.CreateAdminPermissionRequest;
import com.errandbuddy.errandbuddy.Dto.response.ErrandBuddyResponse;
import com.errandbuddy.errandbuddy.Services.AdminPermissionService;
import com.errandbuddy.errandbuddy.utils.PermissionType;
import com.errandbuddy.errandbuddy.utils.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/adminPermission")
public class AdminPermissionController {

    private final AdminPermissionService adminPermissionService;

    @PostMapping("assignPermission")
    public ErrandBuddyResponse assignAdminPermission(@Valid @RequestBody CreateAdminPermissionRequest createAdminPermissionRequest){
        PermissionType permissionType = createAdminPermissionRequest.getPermissionType();
        Resource resource = createAdminPermissionRequest.getResource();
        adminPermissionService.assignPermission(createAdminPermissionRequest.getAdminId(), permissionType, resource);

    return ErrandBuddyResponse.builder()
            .responseMessage("Permission assigned successfully")
            .build();
    }

    @DeleteMapping("revokePermission/{id}")
    public ErrandBuddyResponse revokeAdminPermission(@PathVariable Long id) {
        return ErrandBuddyResponse.builder()
                .responseMessage("Permission revoked successfully")
                .build();
    }

    @GetMapping("permissions/check")
    public boolean checkAdminPermission(@RequestParam Long adminId,
                                        @RequestParam PermissionType permissionType,
                                        @RequestParam Resource resource) {
        return adminPermissionService.hasPermission(adminId, permissionType, resource);
    }



}
