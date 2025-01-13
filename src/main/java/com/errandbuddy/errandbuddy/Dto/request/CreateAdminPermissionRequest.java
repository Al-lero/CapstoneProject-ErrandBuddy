package com.errandbuddy.errandbuddy.Dto.request;

import com.errandbuddy.errandbuddy.utils.PermissionType;
import com.errandbuddy.errandbuddy.utils.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminPermissionRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adminId;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

    @Enumerated(EnumType.STRING)
    private Resource resource;
}
