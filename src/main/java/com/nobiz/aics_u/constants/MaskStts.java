package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MaskStts {
    /* 비 식별화 처리 상태 */

    COMPLETE("05001", "완료"),
    ERROR("05002", "오류"),
    FAIL("05003", "실패") ;
    
    private final String code;
    private final String codeText;

    public static MaskStts getByCode(String code) {
        for (MaskStts status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
