package com.nobiz.aics_u.model.dto.userReq;

import com.nobiz.aics_u.model.dto.UserSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReqApprvSearch {
    /* 로그인 정보로 확인 */
    private String userId;              // 사용자 아이디
    private String passwd;              // 비밀번호 해쉬값(sh256, hex문자열)

    /* 이용자 정보로 확인 */
    private String userNm;              // 이용자 이름
    private String telNo;               // 연락처('-'로 구분)
    private String emailAddr;           // 이메일 주소
}
