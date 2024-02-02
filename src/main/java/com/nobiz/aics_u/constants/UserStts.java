package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStts {
    /* 이용자 상태 */

    NORMAL("07001", "정상"),
    STANDBY("07002", "대기"),
    EXPIRED("07003", "만료"),
    STOP("07004", "정지");

    private final String code;
    private final String codeText;

    public static UserStts getByCode(String code) {
        for (UserStts status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
