package com.nobiz.aics_u.repository.mapper;

import com.nobiz.aics_u.model.dto.clipUsePolicy.ClipUsePolicy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClipUsePolicyMapper {
    ClipUsePolicy selectClipUsePolicy();
}
