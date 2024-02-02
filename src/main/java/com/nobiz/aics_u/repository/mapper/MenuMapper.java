package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.MenuDto;
import com.nobiz.aics_u.model.dto.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
    List<MenuDto> selectAllMenu();
}
