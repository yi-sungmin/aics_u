package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    /* 이용자 구분 */

    ADMIN("09001", "관리자"),
    USER("00002", "이용자");

    private final String code;
    private final String codeText;

}
