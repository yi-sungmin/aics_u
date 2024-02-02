package com.nobiz.aics_u.service.impl;

import com.nobiz.aics_u.model.dto.userType.UserType;
import com.nobiz.aics_u.repository.mapper.UserTypeMapper;
import com.nobiz.aics_u.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeMapper userTypeMapper;

    @Override
    public List<UserType> findAllUserType(String useYn) {
        List<UserType> userTypes = userTypeMapper.selectAllUserType(useYn);
        return userTypeMapper.selectAllUserType(useYn);
    }
}
