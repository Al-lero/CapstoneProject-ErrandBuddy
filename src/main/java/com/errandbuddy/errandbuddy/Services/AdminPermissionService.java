package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.utils.PermissionType;
import com.errandbuddy.errandbuddy.utils.Resource;

public interface AdminPermissionService {

    void assignPermission(Long adminId, PermissionType permissionType, Resource resource);
    void revokePermission(Long id);
    Boolean hasPermission(Long adminId, PermissionType permissionType, Resource resource);
}
