package com.UserManager.model.dto.role;

import com.UserManager.enums.Roles;
import com.UserManager.model.dto.base.AddBaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddRoleDto extends AddBaseDto {

    private Roles roleName;

    private String description;
}
