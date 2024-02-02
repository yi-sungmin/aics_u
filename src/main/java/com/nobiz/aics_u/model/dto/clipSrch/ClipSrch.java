package com.nobiz.aics_u.model.dto.clipSrch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClipSrch {
    private long clipId;        // 클립 아이디 (PK)
    private long fileId;        // 파일 아이디 (PK)
    private String fileNm;      // 파일명
    private double clipSize;    // 클립(원본) 크기
    private String objectType;  // 대상
    private String createDtm;   // 생성일
    private int thumbCnt;       // 썸네일 개수
    private double maskSize;    // 비식별화 파일 총 크기
    private String maskCreateDtm;   // 생성일
}
