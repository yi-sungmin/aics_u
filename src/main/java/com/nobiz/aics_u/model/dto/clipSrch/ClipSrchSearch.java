package com.nobiz.aics_u.model.dto.clipSrch;

import com.nobiz.aics_u.model.dto.PageDto;
import com.nobiz.aics_u.model.dto.UserSession;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClipSrchSearch extends PageDto {
    private String ordering;            // asc : 과거순, desc : 최신순
    private String startDate;           // 시작일
    private String endDate;             // 종료일
    private String operation;           // 논리연산
    private String stts;                // 상태
    private List<String> objectTypes;   // 식별객체 (String 으로 붙여옴)
    private int objectSize;
}
