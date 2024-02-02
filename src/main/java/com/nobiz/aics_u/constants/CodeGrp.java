package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeGrp {

    /* 그룹 코드 */

    CLIP_REQ_TYPE("01", "클리핑 요청 유형"),
    CLIP_PROCESS_STTS("02", "클리핑 처리 상태"),
    OBJECT_TYPE("03", "식별 객체"),
    MASK_REQ_TYPE("04", "비 식별화 요청 유형"),
    MASK_STTS("05", "비 식별화 처리 상태"),
    REQ_STTS("06", "이용자/클립 이용 신청 상태"),
    USER_STTS("07", "이용자 상태"),
    USER_ADD_TYPE("08", "이용자 등록 구분"),
    USER_TYPE("09", "이용자 구분"),
    CLIP_USE_TYPE("10", "클립 이용 유형"),
    CLIP_FILE_TYPE("11", "클립 파일 유형");

    private final String code;
    private final String codeText;

}
