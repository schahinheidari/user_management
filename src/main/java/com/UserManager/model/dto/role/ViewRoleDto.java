package com.UserManager.model.dto.role;

import com.UserManager.enums.Roles;
import com.UserManager.model.dto.base.ViewBaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewRoleDto extends ViewBaseDto {

    private Roles roleName;

    private String roleDescription;
}
