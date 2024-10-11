package com.UserManager.model.dto.user;

import com.UserManager.model.dto.base.ViewBaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewUserDto extends ViewBaseDto {
    private String name;
    private String family;
    private long nationalCode;
    private String genderString;
    private Long birthDayTimeStamp;
    private String username;
}
