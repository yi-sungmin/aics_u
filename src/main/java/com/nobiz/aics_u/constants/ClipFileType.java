package com.nobiz.aics_u.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClipFileType {
    /* 클립 파일 유형 */

    CLIP("11001", "원본 클립"),
    MASK("11002", "비식별화 클립"),
    THUMB("11003", "학습용 썸네일");

    private final String code;
    private final String codeText;

}
