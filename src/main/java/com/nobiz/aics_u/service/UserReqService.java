package com.nobiz.aics_u.service;

import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.userReq.UserReqApprvSearch;
import com.nobiz.aics_u.model.dto.userReq.UserReqCreate;

public interface UserReqService {
    ResponseDto<Object> createUserReq(UserReqCreate userReqCreate);
    ResponseDto<Object> reCreateUserReq(UserReqCreate userReqCreate);
    ResponseDto<?> findApprvByLoginInfo(UserReqApprvSearch userReqApprvSearch);
    ResponseDto<?> findApprvByUserInfo(UserReqApprvSearch userReqApprvSearch);
}
