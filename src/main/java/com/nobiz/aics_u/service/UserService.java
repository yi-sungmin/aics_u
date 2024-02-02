package com.nobiz.aics_u.service;

import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.user.User;
import com.nobiz.aics_u.model.dto.user.UserPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserTempPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserRegSearch;

import java.util.List;

public interface UserService {
    User addUser(User memberVo);
    List<User> findAllUser();
    User modifyUser(User memberVo);
    ResponseDto<Boolean> isCheckedId(String userId);
    ResponseDto<String> findGeneratePasswd();
    ResponseDto<Object> modifyTempPasswd(UserTempPasswdUpdate userTempPasswdUpdate);
    ResponseDto<UserRegSearch> findUserIdAndStts(UserRegSearch userRegSearch);
    ResponseDto<Object> modifyPasswd(UserPasswdUpdate userPasswdUpdate);
}
