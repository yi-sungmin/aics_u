package com.nobiz.aics_u.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSession {
    /* 사용자 세션 객체 */
    private String loginUserId;
    private String loginUserNm;
    private String loginEmailAddr;
    private String loginTelNo;
    private long loginAuthId;
    private String loginAuthNm;
    private String loginDtm;
}
