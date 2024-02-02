package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClipStts {
    /* 클리핑 처리 상태 */

    REQUEST("02001", "요청"),
    COMPLETE("02002", "완료"),
    CANCEL("02003", "오류"),
    ERROR("02004", "취소");

    private final String code;
    private final String codeText;

    public static ClipStts getByCode(String code) {
        for (ClipStts status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
