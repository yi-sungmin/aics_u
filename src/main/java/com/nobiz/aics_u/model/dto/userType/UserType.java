package com.nobiz.aics_u.model.dto.userType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserType {
    private long typeId;
    private String typeNm;
    private int ord;
    private String useYn;
    private String createId;
    private String createDtm;
    private String updateId;
    private String updateDtm;
}
