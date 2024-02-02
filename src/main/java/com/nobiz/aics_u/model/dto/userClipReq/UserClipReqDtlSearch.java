package com.nobiz.aics_u.model.dto.userClipReq;

import com.nobiz.aics_u.model.dto.PageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class UserClipReqDtlSearch extends PageDto {
    private long reqId;                 // 요청 아이디 (PK)
}
