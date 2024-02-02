package com.nobiz.aics_u.model.dto.clipUsePolicy;

import com.nobiz.aics_u.model.dto.UserSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClipUsePolicy extends UserSession {
    private String clipPlayYn;  // 클립(원본) 재생 허용 여부
    private String clipSaveYn;  // 클립(원본) 저장 허용 여부
    private String maskPlayYn;  // 클립(비식별화) 재생 허용 여부
    private String maskSaveYn;  // 클립(비식별화) 저장 허용 여부
    private String thumbViewYn; // 썸네일 조회 허용 여부
    private String thumbSaveYn; // 썸네일 저장 허용 여부
    private String updateId;    // 수정자
    private String updateDtm;   // 수정일자
}
