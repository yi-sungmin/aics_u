package com.nobiz.aics_u.model.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginHist {
    private String loginId;
    private String userType;
    private String ipAddr;
    private String loginDtm;
}
