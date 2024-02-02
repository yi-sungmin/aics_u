package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.userType.UserType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTypeMapper {
    List<UserType> selectAllUserType(String userYn);
}
