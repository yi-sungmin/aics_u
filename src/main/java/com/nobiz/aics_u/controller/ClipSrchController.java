package com.nobiz.aics_u.controller;

import com.nobiz.aics_u.constants.ClipStts;
import com.nobiz.aics_u.constants.CodeGrp;
import com.nobiz.aics_u.constants.MaskStts;
import com.nobiz.aics_u.constants.UserStts;
import com.nobiz.aics_u.model.dto.DataTableResult;
import com.nobiz.aics_u.model.dto.clipSrch.ClipSrch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipSrchSearch;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumb;
import com.nobiz.aics_u.model.dto.clipSrch.ClipThumbSearch;
import com.nobiz.aics_u.service.ClipUsePolicyService;
import com.nobiz.aics_u.service.impl.ClipSrchServiceImpl;
import com.nobiz.aics_u.service.impl.CodeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/clip-srch")
public class ClipSrchController {

    private final ClipSrchServiceImpl clipSrchService;
    private final ClipUsePolicyService clipUserPlicyService;
    private final CodeServiceImpl codeService;

    @GetMapping
    public String clipSrchPage(Model model) {
        model.addAttribute("objectTypes", codeService.getCodesByCodeGrp(CodeGrp.OBJECT_TYPE.getCode()));
        model.addAttribute("policy", clipUserPlicyService.findClipUsePolicy());
        model.addAttribute("clipComplete", ClipStts.COMPLETE.getCode());
        model.addAttribute("maskComplete", MaskStts.COMPLETE.getCode());
        return "clip/clipSrch";
    }

    @GetMapping("/all")
    @ResponseBody
    public DataTableResult<List<ClipSrch>> findAllClipSrch(ClipSrchSearch clipSrchSearch) {
        return clipSrchService.findAllClipSrch(clipSrchSearch);
    }

    @GetMapping("/findAllThumbById")
    @ResponseBody
    public DataTableResult<List<ClipThumb>> findAllThumbById(ClipThumbSearch clipThumbSearch) {
        return clipSrchService.findAllThumbById(clipThumbSearch);
    }
}
