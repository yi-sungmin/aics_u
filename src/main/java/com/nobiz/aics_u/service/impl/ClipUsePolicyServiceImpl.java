package com.nobiz.aics_u.service.impl;

import com.nobiz.aics_u.model.dto.clipUsePolicy.ClipUsePolicy;
import com.nobiz.aics_u.repository.mapper.ClipUsePolicyMapper;
import com.nobiz.aics_u.service.ClipUsePolicyService;
import com.nobiz.aics_u.utils.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClipUsePolicyServiceImpl implements ClipUsePolicyService {

    private final ClipUsePolicyMapper clipUsePolicyMapper;
    private final MessageSourceUtil ms;

    @Override
    public ClipUsePolicy findClipUsePolicy() {
        return clipUsePolicyMapper.selectClipUsePolicy();
    }
}
