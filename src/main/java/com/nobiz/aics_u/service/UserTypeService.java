package com.nobiz.aics_u.service;

import com.nobiz.aics_u.model.dto.userType.UserType;

import java.util.List;

public interface UserTypeService {
    List<UserType> findAllUserType(String useYn);

}
