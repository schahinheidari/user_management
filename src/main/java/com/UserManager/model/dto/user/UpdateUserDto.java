package com.UserManager.model.dto.user;

import com.UserManager.model.dto.base.UpdateBaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Setter
@Getter
public class UpdateUserDto extends UpdateBaseDto {
    @NotBlank
    private String name;

    @NotBlank
    private String family;

    @NotBlank
    private String genderString;

    @NotNull
    private Long birthDayTimeStamp;

    @NotBlank
    private String password;
}
