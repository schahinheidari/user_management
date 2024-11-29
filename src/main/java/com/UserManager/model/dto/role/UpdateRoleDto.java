package com.UserManager.model.dto.role;

import com.UserManager.enums.Roles;
import com.UserManager.model.dto.base.UpdateBaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleDto extends UpdateBaseDto {

    private Roles roleName;

    private String description;
}
