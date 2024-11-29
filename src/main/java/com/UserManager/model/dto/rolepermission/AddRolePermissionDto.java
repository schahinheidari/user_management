package com.UserManager.model.dto.rolepermission;

import com.UserManager.enums.Permissions;
import com.UserManager.enums.Roles;
import com.UserManager.model.dto.base.AddBaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddRolePermissionDto extends AddBaseDto {

    private Roles roleId;

    private Permissions permissionId;
}
