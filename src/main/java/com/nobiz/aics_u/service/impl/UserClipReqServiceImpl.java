package com.nobiz.aics_u.service.impl;

import com.nobiz.aics_u.constants.UserAndClipReqStts;
import com.nobiz.aics_u.exception.NoRowsAffectedException;
import com.nobiz.aics_u.model.dto.DataTableResult;
import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.userClipReq.*;
import com.nobiz.aics_u.repository.mapper.UserClipReqMapper;
import com.nobiz.aics_u.service.UserClipReqService;
import com.nobiz.aics_u.utils.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserClipReqServiceImpl implements UserClipReqService {
    private final UserClipReqMapper userClipReqMapper;
    private final MessageSourceUtil ms;

    @Override
    public DataTableResult<List<UserClipReq>> findAllUserClipReq(UserClipReqSearch userClipReqSearch) {
        List<UserClipReq> userClipReqs = userClipReqMapper.selectAllUserClipReq(userClipReqSearch);
        int recordsFiltered = userClipReqMapper.selectAllUserClipReqCnt(userClipReqSearch);
        return new DataTableResult<>(userClipReqs, recordsFiltered);
    }

    @Override
    @Transactional(rollbackFor = NoRowsAffectedException.class)
    public ResponseDto<Object> createUserClipReq(UserClipReqCreate userClipReqCreate) {
        /**
         * 1. 클립 이용 신청 등록 (tb_user_clip_req)
         * 2. 등록하고 리턴 받은 reqId 값을 가지고 디테일 테이블에 클립정보 등록 (tb_user_clip_req_dtl)
         */
        try {
            userClipReqCreate.setStts(UserAndClipReqStts.REQUEST.getCode());
            userClipReqMapper.insertClipReq(userClipReqCreate);

            int result = userClipReqMapper.insertClipReqDtl(userClipReqCreate);
            if (result == 0) {
                throw new NoRowsAffectedException(ms.getMessage("affect.insert.fail"));
            }
            return new ResponseDto<>(null, ms.getMessage("request.ok"));
        } catch(NoRowsAffectedException ex) {
            throw new NoRowsAffectedException(ms.getMessage("affect.insert.fail"));
        }
    }

    @Override
    public DataTableResult<List<UserClipReqDtl>> findAllUserClipReqDtl(UserClipReqDtlSearch userClipReqDtlSearch) {
        List<UserClipReqDtl> clipReqDtl = userClipReqMapper.selectAllUserClipReqDtl(userClipReqDtlSearch);
        int recordsFiltered = userClipReqMapper.selectAllUserClipReqDtlCnt(userClipReqDtlSearch);
        return new DataTableResult<>(clipReqDtl, recordsFiltered);
    }

}
