package com.nobiz.aics_u.service.impl;

import com.nobiz.aics_u.constants.UserAndClipReqStts;
import com.nobiz.aics_u.constants.UserStts;
import com.nobiz.aics_u.exception.NoRowsAffectedException;
import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.user.UserSttsUpdate;
import com.nobiz.aics_u.model.dto.userReq.UserReq;
import com.nobiz.aics_u.model.dto.userReq.UserReqApprvSearch;
import com.nobiz.aics_u.model.dto.userReq.UserReqCreate;
import com.nobiz.aics_u.repository.mapper.UserReqMapper;
import com.nobiz.aics_u.service.UserReqService;
import com.nobiz.aics_u.utils.MessageSourceUtil;
import com.nobiz.aics_u.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = {NoRowsAffectedException.class, RuntimeException.class})
public class UserReqServiceImpl implements UserReqService {

    private final UserReqMapper userReqMapper;
    private final MessageSourceUtil ms;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseDto<Object> createUserReq(UserReqCreate userReqCreate) {
        /* 상태 값 세팅 */
        userReqCreate.setStts(UserAndClipReqStts.REQUEST.getCode());
        userReqCreate.setPasswd(PasswordUtil.encodePassword(userReqCreate.getPasswd(), passwordEncoder));

        int result = userReqMapper.insertUserReq(userReqCreate);
        if (result == 0) {
            throw new NoRowsAffectedException(ms.getMessage("affect.insert.fail"));
        }
        return new ResponseDto<>(null, ms.getMessage("request.ok"));
    }

    @Override
    public ResponseDto<Object> reCreateUserReq(UserReqCreate userReqCreate) {
        /**
         * 기존 정보 재등록 > 업데이트
         * 1. 사용자(tb_user) 상태 업데이트 (대기상태) -> UserStts.STANDBY.getCode();
         * 2. 기존 요청
         */
        try {
            UserSttsUpdate userSttsUpdate = new UserSttsUpdate();
            userSttsUpdate.setUserId(userReqCreate.getUserId());
            userSttsUpdate.setStts(UserStts.STANDBY.getCode());
            userReqMapper.updateUserStts(userSttsUpdate);

            return createUserReq(userReqCreate);

        } catch (NoRowsAffectedException ex) {
            throw new NoRowsAffectedException(ex.getMessage());
        }
    }

    @Override
    public ResponseDto<UserReq> findApprvByLoginInfo(UserReqApprvSearch userReqApprvSearch) {
        UserReq userReq = userReqMapper.selectApprvByLoginInfo(userReqApprvSearch);
        if (userReq == null) {
            userReq = new UserReq();
            userReq.setStts("없음");
        }
        else {
            /* 비밀번호 매칭 확인 */
            boolean isMatch = passwordEncoder.matches(userReqApprvSearch.getPasswd(), userReq.getPasswd());
            if (isMatch) {
                userReq.setPasswd("");
            } else {
                userReq = new UserReq();
                userReq.setStts("없음");
            }
        }

        return new ResponseDto<>(userReq, ms.getMessage("request.ok"));
    }

    @Override
    public ResponseDto<UserReq> findApprvByUserInfo(UserReqApprvSearch userReqApprvSearch) {
        UserReq userReq = userReqMapper.selectApprvByUserInfo(userReqApprvSearch);
        if (userReq == null) {
            userReq = new UserReq();
            userReq.setStts("없음");
        }
        return new ResponseDto<>(userReq, ms.getMessage("request.ok"));
    }
}
