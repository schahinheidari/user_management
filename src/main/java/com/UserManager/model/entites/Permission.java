package com.UserManager.model.entites;

import com.UserManager.enums.Permissions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {
    private Permissions permissionName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermissionsList;


}
