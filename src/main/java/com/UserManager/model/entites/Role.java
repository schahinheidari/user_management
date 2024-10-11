package com.UserManager.model.entites;


import com.UserManager.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roleName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "USER_ROLE")
    private List<UserRole> userRoleList;

    @OneToMany(mappedBy = "ROLE_PERMISSION")
    private List<RolePermission> rolePermissionsList;

    private boolean is_deletable = false;
}
