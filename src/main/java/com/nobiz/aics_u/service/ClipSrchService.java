package com.nobiz.aics_u.service;

import com.nobiz.aics_u.model.dto.DataTableResult;
import com.nobiz.aics_u.model.dto.clipSrch.ClipSrch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipSrchSearch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumb;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumbSearch;

import java.util.List;

public interface ClipSrchService {
    DataTableResult<List<ClipSrch>> findAllClipSrch(ClipSrchSearch clipSrchSearch);
    DataTableResult<List<ClipThumb>> findAllThumbById(ClipThumbSearch clipThumbSearch);
}
