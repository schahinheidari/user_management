package com.UserManager.model.dto.rolepermission;

import com.UserManager.enums.Permissions;
import com.UserManager.enums.Roles;
import com.UserManager.model.dto.base.ViewBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ViewRolePermissionDto extends ViewBaseDto {

    private Roles roleId;

    private Permissions permissionId;

    private Date grantedDate;
}
