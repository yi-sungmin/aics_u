package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserReqType {
    /* 이용자 등록 구분 */

    ONLINE("08001", "온라인"),
    ADMIN("08002", "관리자");

    private final String code;
    private final String codeText;

}
