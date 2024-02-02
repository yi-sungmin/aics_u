package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.user.User;
import com.nobiz.aics_u.model.dto.user.UserPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserTempPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserRegSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> selectUserById(String userId);
    boolean selectExistsUserId(String userId);
    int updateTempPasswd(UserTempPasswdUpdate userTempPasswdUpdate);
    int updatePasswd(UserPasswdUpdate userPasswdUpdate);
    UserRegSearch selectUserIdAndStts(UserRegSearch userRegSearch);
}
