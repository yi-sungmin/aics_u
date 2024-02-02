package com.nobiz.aics_u.model.dto.userReq;

import com.nobiz.aics_u.model.dto.UserSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReq {
    private long reqId;                 // 요청 아이디 (PK)

    private String userId;              // 사용자 아이디
    private String passwd;              // 비밀번호 해쉬값(sh256, hex문자열)
    private String userNm;              // 이용자 이름
    private long typeId;                // 이용자 유형(기업, 연구소, etc.. *tb_user_type PK)
    private String typeNm;              // 이용자 유형명
    private String orgNm;               // 소속 이름(기업명, 기관명, 조직명, etc)
    private String telNo;               // 연락처('-'로 구분)
    private String emailAddr;           // 이메일 주소
    private String startDtm;            // 이용 시작일시
    private String endDtm;              // 이용 종료일시
    private String purpose;             // 이용 목적
    private String stts;                // 상태, 코드로 관리
    private String rejectReason;        // 이용정지 사유
    private String apprvId;             // 승이/거절 관리자 아이디
    private String reqDtm;              // 요청 일시
    private String apprvDtm;            // 승인/거절 일시
}
