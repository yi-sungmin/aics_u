package com.nobiz.aics_u.model.dto.userClipReq;

import com.nobiz.aics_u.model.dto.PageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserClipReqSearch extends PageDto {
    private String typeId;      // 유형
    private String orgNm;       // 소속명
    private String userNm;      // 이용자명
    private String userId;      // 이용자 아이디
    private String stts;        // 상태 (tb_code 테이블 id 로 관리)
    private String startDate;   // 신청일 시작
    private String endDate;     // 신청일 종료
}
