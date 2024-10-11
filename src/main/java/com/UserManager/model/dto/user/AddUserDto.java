package com.UserManager.model.dto.user;

import com.UserManager.model.dto.base.AddBaseDto;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
public class AddUserDto extends AddBaseDto {
    @NotBlank
    private String name;

    @NotBlank
    private String family;

    @Positive
    private long nationalCode;

    @NotBlank
    private String genderString;

    @NotNull
    private Long birthDayTimeStamp;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
