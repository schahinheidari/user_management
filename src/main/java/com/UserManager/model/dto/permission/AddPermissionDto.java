package com.UserManager.model.dto.permission;

import com.UserManager.enums.Permissions;
import com.UserManager.model.dto.base.AddBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AddPermissionDto extends AddBaseDto {
    private Permissions permissionName;

    private String description;

    private Date created;
}
