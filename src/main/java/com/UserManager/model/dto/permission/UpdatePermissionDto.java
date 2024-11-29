package com.UserManager.model.dto.permission;

import com.UserManager.enums.Permissions;
import com.UserManager.model.dto.base.UpdateBaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePermissionDto extends UpdateBaseDto {

    private Permissions permissionName;

    private String description;
}
