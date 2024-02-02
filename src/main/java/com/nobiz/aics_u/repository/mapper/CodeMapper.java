package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.common.Code;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {
    List<Code> selectAllCode();
}
