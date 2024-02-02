package com.nobiz.aics_u.model.dto.user;

import com.nobiz.aics_u.model.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends PageDto implements UserDetails {
    private String userId;              // 사용자 아이디 (PK)
    private String passwd;              // 비밀번호 해쉬값(sh256, hex문자열)
    private String userNm;              // 이용자 이름
    private long typeId;                // 이용자 유형(기업, 연구소, etc.. *tb_user_type PK)
    private String orgNm;               // 소속 이름(기업명, 기관명, 조직명, etc)
    private String telNo;               // 연락처('-'로 구분)
    private String emailAddr;           // 이메일 주소
    private String startDtm;            // 이용 시작일시
    private String endDtm;              // 이용 종료일시
    private String tempPasswdYn;        // 임시 비밀번호 여부
    private String stts;                // 상태, 코드로 관리
    private String stopReason;          // 이용정지 사유
    private String stopDtm;             // 이용정지 일시
    private String stopId;              // 이용정지 처리 관리자
    private String reqType;             // 등록 유형(온라인/관리자), 코드로 관리

    private String purpose;             // ** 이용목적(req 테이블에만 있음 이거 확인 필요)
    private String loginDtm;            // 최근 로그인
    private int loginCnt;               // 로그인 횟수
    private String typeNm;              // 유형명

    private String reqDtm;              // 신청일
    private String createId;
    private String createDtm;
    private String updateId;
    private String updateDtm;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return roles;
    }
    @Override
    public String getPassword() {
        return this.passwd;
    }
    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
