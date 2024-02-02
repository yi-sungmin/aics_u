package com.nobiz.aics_u.model.dto.userClipReq;

import com.nobiz.aics_u.model.dto.PageDto;
import com.nobiz.aics_u.model.dto.UserSession;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserClipReqCreate extends UserSession {
    private long reqId;                 // 요청 아이디 (PK)
    private long clipId;                // 요청 아이디 (PK)
    private String userId;              // 사용자 아이디
    private String startDtm;            // 이용 시작일시
    private String endDtm;              // 이용 종료일시
    private String purpose;             // 이용 목적
    private String stts;                // 상태, 코드로 관리
    private String rejectReason;        // 이용정지 사유
    private String apprvId;             // 승인/거절 관리자 아이디
    private String reqDtm;              // 요청 일시
    private String apprvDtm;            // 승인/거절 일시

    List<UserClipReqDtl> clipReqDtl;
}
