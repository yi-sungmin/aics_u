package com.nobiz.aics_u.service.impl;

import com.nobiz.aics_u.model.dto.common.Code;
import com.nobiz.aics_u.repository.mapper.CodeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeServiceImpl {

    /* 공통 코드 조회 싱글톤 */

    public CodeServiceImpl(CodeMapper codeMapper) {
        this.commonCodes = codeMapper.selectAllCode();
    }

    private final List<Code> commonCodes;

    /**
     * 전체 코드 조회 (사용중)
     * @return List<Code> 
     */
    public List<Code> getCodes() {
        return commonCodes;
    }

    /**
     * codeGrp 값으로 코드 리스트 조회
     * @param codeGrp(String) : CodeGrp.상태코드.getCode(); 로 확인
     * @return List<Code>
     */
    public List<Code> getCodesByCodeGrp(String codeGrp) {
        return this.commonCodes.stream()
                .filter(code -> code.getCodeGrp().equals(codeGrp))
                .collect(Collectors.toList());
    }
}
