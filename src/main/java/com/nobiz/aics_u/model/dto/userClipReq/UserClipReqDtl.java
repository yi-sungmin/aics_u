package com.nobiz.aics_u.model.dto.userClipReq;

import com.nobiz.aics_u.model.dto.UserSession;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserClipReqDtl extends UserSession {

    private long clipId;            // 클립 아이디 (PK)
    private long fileId;            // 파일 아이디 (PK)
    private String fileNm;          // 파일명
    private double clipSize;        // 클립(원본) 크기
    private double maskSize;        // 비식별 파일 크기
    private String objectType;      // 대상
    private String createDtm;       // 생성일
    private int thumbCnt;          // 썸네일 개수
    private String maskCreateDtm;   // 생성일
    private String clipPlayYn;      // 클립(원본) 재생 여부(Y or N)
    private String clipSaveYn;      // 클립(원본) 저장 여부(Y or N)
    private String maskPlayYn;      // 클립(비 식별화) 재생 여부(Y or N)
    private String maskSaveYn;      // 클립(비 식별화) 저장 여부(Y or N)
    private String thumbViewYn;     // 학습용 썸네일 조회 여부(Y or N)
    private String thumbSaveYn;     // 학습용 썸네일 저장 여부(Y or N)
    private String fileLink;        // 승인된 경우 파일 Symbolic Link 경로
}
