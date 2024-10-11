package com.UserManager.model.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class RolePermission extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_PERMISSION")
    @NotNull
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERMISSION_ID")
    @NotNull
    private Permission permission;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date granted_date;
}
