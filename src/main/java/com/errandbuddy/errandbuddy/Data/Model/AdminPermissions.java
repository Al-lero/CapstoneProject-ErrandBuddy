package com.errandbuddy.errandbuddy.Data.Model;

import com.errandbuddy.errandbuddy.utils.PermissionType;
import com.errandbuddy.errandbuddy.utils.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adminId;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

    @Enumerated(EnumType.STRING)
    private Resource resource;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
