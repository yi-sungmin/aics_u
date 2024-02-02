package com.nobiz.aics_u.service;

import com.nobiz.aics_u.model.dto.DataTableResult;
import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.userClipReq.*;
import com.nobiz.aics_u.model.dto.userReq.UserReqApprvSearch;
import com.nobiz.aics_u.model.dto.userReq.UserReqCreate;

import java.util.List;

public interface UserClipReqService {
    DataTableResult<List<UserClipReq>> findAllUserClipReq(UserClipReqSearch userClipReqSearch);
    ResponseDto<Object> createUserClipReq(UserClipReqCreate userClipReqCreate);
    DataTableResult<List<UserClipReqDtl>> findAllUserClipReqDtl(UserClipReqDtlSearch userClipReqDtlSearch);
}
