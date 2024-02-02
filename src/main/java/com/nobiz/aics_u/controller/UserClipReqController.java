package com.nobiz.aics_u.controller;

import com.nobiz.aics_u.constants.UserAndClipReqStts;
import com.nobiz.aics_u.model.dto.DataTableResult;
import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.userClipReq.*;
import com.nobiz.aics_u.service.impl.UserClipReqServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user-clip-req")
public class UserClipReqController {

    private final UserClipReqServiceImpl userClipReqService;

    @GetMapping
    public String userClipReqPage(Model model) {
        model.addAttribute("clipReqStts", UserAndClipReqStts.values());
        return "user/userClipReq";
    }

    @GetMapping("/all")
    @ResponseBody
    public DataTableResult<List<UserClipReq>> findAllUserClipReq(UserClipReqSearch userClipReqSearch) {
        return userClipReqService.findAllUserClipReq(userClipReqSearch);
    }
    @GetMapping("/dtl/all")
    @ResponseBody
    public DataTableResult<List<UserClipReqDtl>> findAllUserClipReqDtl(UserClipReqDtlSearch userClipReqDtlSearch) {
        return userClipReqService.findAllUserClipReqDtl(userClipReqDtlSearch);
    }

    @PostMapping
    @ResponseBody
    public ResponseDto<Object> createUserClipReq(@RequestBody UserClipReqCreate userClipReqCreate) {
        return userClipReqService.createUserClipReq(userClipReqCreate);
    }
}
