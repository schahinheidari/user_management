package com.UserManager.model.dto.permission;

import com.UserManager.enums.Permissions;
import com.UserManager.model.dto.base.ViewBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ViewPermissionDto extends ViewBaseDto {
    private Permissions permissionName;

    private String description;

    private Date created;

    private Date updated;
}
