package com.nobiz.aics_u.model.dto.userReq;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReqCreate {
    @NotBlank(message = "{required.user.id}")
    private String userId;              // 사용자 아이디

    @NotBlank(message = "{required.user.passwd}")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자 조합하여 사용하세요.")
    private String passwd;              // 비밀번호 해쉬값(sh256, hex문자열)

    @NotBlank(message = "{required.user.name}")
    private String userNm;              // 이용자 이름

    @Min(value=1, message = "{required.user.typeId}")
    private long typeId;                // 이용자 유형(기업, 연구소, etc.. *tb_user_type PK)

    @NotBlank(message = "{required.user.orgNm}")
    private String orgNm;               // 소속 이름(기업명, 기관명, 조직명, etc)

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "{valid.telno.rule}")
    private String telNo;               // 연락처('-'로 구분)

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "{valid.email.rule}")
    private String emailAddr;           // 이메일 주소

    @Pattern(regexp = "^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}$", message = "{valid.sdate.rule}")
    private String startDtm;            // 이용 시작일시
    @Pattern(regexp = "^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}$", message = "{valid.edate.rule}")
    private String endDtm;              // 이용 종료일시

    private String purpose;             // 이용 목적

    private String stts;                // 상태, 코드로 관리
}
