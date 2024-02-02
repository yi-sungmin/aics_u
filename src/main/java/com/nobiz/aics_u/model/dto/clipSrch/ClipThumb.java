package com.nobiz.aics_u.model.dto.clipSrch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClipThumb {
    private long clipId;        // 클립 아이디 (PK)
    private long fileId;        // 파일 아이디 (PK)
    private String fileNm;      // 파일명
    private double fileSize;    // 클립(원본) 크기
    private String objectId;    // 대상 아이디
    private String objectType;  // 대상 명칭 (code_text)
    private double bboxTop;     // top 좌표
    private double bboxLeft;    // top 좌표
    private double bboxRight;   // top 좌표
    private double bboxBottom;  // top 좌표
    private String createDtm;   // 썸네일 생성일
}
