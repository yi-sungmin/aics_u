package com.nobiz.aics_u.model.dto.clipSrch;

import com.nobiz.aics_u.model.dto.PageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClipThumbSearch extends PageDto {
    private long clipId;        // 클립 아이디 (PK)
    private String fileType;    // 클립 파일 타입
}
