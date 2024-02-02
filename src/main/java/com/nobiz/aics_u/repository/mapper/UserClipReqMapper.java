package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.userClipReq.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserClipReqMapper {
    /* 이용자 클립 이용 신청 전체 조회 */
    List<UserClipReq> selectAllUserClipReq(UserClipReqSearch userClipReqSearch);
    /* 이용자 클립 이용 신청 전체 개수 조회 */
    int selectAllUserClipReqCnt(UserClipReqSearch userClipReqSearch);
    void insertClipReq(UserClipReqCreate userClipReqCreate);
    int insertClipReqDtl(UserClipReqCreate userClipReqCreate);
    List<UserClipReqDtl> selectAllUserClipReqDtl(UserClipReqDtlSearch userClipReqDtlSearch);
    int selectAllUserClipReqDtlCnt(UserClipReqDtlSearch userClipReqDtlSearch);
}

