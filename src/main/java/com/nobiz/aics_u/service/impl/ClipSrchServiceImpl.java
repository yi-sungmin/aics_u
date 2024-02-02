package com.nobiz.aics_u.service.impl;

import com.nobiz.aics_u.constants.ClipFileType;
import com.nobiz.aics_u.model.dto.DataTableResult;
import com.nobiz.aics_u.model.dto.clipSrch.ClipSrch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipSrchSearch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumb;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumbSearch;
import com.nobiz.aics_u.repository.mapper.ClipSrchMapper;
import com.nobiz.aics_u.service.ClipSrchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClipSrchServiceImpl implements ClipSrchService {
    private final ClipSrchMapper clipSrchMapper;


    @Override
    public DataTableResult<List<ClipSrch>> findAllClipSrch(ClipSrchSearch clipSrchSearch) {
        List<ClipSrch> data = clipSrchMapper.selectAllClipSrch(clipSrchSearch);
        int filteredRecords = clipSrchMapper.selectAllClipSrchCnt(clipSrchSearch);
        return new DataTableResult<>(data, filteredRecords);
    }

    @Override
    public DataTableResult<List<ClipThumb>> findAllThumbById(ClipThumbSearch clipThumbSearch) {
        clipThumbSearch.setFileType(ClipFileType.THUMB.getCode());
        List<ClipThumb> data = clipSrchMapper.selectAllThumbById(clipThumbSearch);
        int filteredRecords = clipSrchMapper.selectAllThumbByIdCnt(clipThumbSearch);
        return new DataTableResult<>(data, filteredRecords);
    }
}
