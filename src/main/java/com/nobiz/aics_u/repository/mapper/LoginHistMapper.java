package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.login.LoginHist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginHistMapper {
    void insertLoginHist(LoginHist loginHist);

    void updateLoginDtm(String loginUserId);
}
