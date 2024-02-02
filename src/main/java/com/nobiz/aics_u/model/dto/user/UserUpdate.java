package com.nobiz.aics_u.model.dto.user;

import com.nobiz.aics_u.model.dto.UserSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdate  extends UserSession {
    @NotBlank(message = "{required.input.value}")
    private String userId;              // 이용자 아이디
    private String passwd;              // 패스워드

    @NotBlank(message = "{required.input.value}")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$", message = "전화번호 형식이 올바르지 않습니다.")
    private String telNo;               // 연락처('-'로 구분)

    private String purpose;             // **이용목적(req 테이블에만 있음 이거 확인 필요)

    @NotBlank(message = "{required.input.value}")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String emailAddr;           // 이메일 주소

    private String startDtm;            // 이용 시작일시
    private String endDtm;              // 이용 종료일시
    private String reqType;             // 이용자 등록 유형 (코드)
    private String tempPasswdYn;        // 임시 비밀번호 여부
    private String stts;
}
