package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.user.UserSttsUpdate;
import com.nobiz.aics_u.model.dto.userReq.UserReq;
import com.nobiz.aics_u.model.dto.userReq.UserReqApprvSearch;
import com.nobiz.aics_u.model.dto.userReq.UserReqCreate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserReqMapper {
    int insertUserReq(UserReqCreate userReqCreate);
    int updateUserStts(UserSttsUpdate userSttsUpdate);
    UserReq selectApprvByLoginInfo(UserReqApprvSearch userReqApprvSearch);
    UserReq selectApprvByUserInfo(UserReqApprvSearch userReqApprvSearch);
}

