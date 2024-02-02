package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.clipSrch.ClipSrch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipSrchSearch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumb;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumbSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClipSrchMapper {
    List<ClipSrch> selectAllClipSrch(ClipSrchSearch clipSrchSearch);
    int selectAllClipSrchCnt(ClipSrchSearch clipSrchSearch);
    List<ClipThumb> selectAllThumbById(ClipThumbSearch clipThumbSearch);
    int selectAllThumbByIdCnt(ClipThumbSearch clipThumbSearch);
}

