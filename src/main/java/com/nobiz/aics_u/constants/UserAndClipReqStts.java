package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAndClipReqStts {
    /* 이용자 & 클립 이용 신청 상태 */

    REQUEST("06001", "신청"),
    APPROVE("06002", "승인"),
    REJECT("06003", "거절");

    private final String code;
    private final String codeText;

}
