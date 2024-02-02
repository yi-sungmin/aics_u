package com.nobiz.aics_u.model.dto.user;

import com.nobiz.aics_u.model.dto.UserSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserTempPasswdUpdate extends UserSession {
    @NotBlank(message = "{required.input.value}")
    private String userId;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "{valid.passwd.rule}")
    private String passwd;
}
