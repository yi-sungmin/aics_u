package com.nobiz.aics_u.controller;

import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.userReq.UserReq;
import com.nobiz.aics_u.model.dto.userReq.UserReqApprvSearch;
import com.nobiz.aics_u.model.dto.userReq.UserReqCreate;
import com.nobiz.aics_u.service.impl.UserReqServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user-req")
public class UserReqController {
    private final UserReqServiceImpl userReqService;
    /**
     * 이용자 등록 요청
     * @param userReqCreate
     * @return ResponseDto
     */
    @PostMapping
    @ResponseBody
    public ResponseDto<Object> createUserReq(@RequestBody @Valid UserReqCreate userReqCreate) {
        return userReqService.createUserReq(userReqCreate);
    }

    /**
     * 이용자 재등록 요청
     * @return
     */
    @PostMapping("/re")
    @ResponseBody
    public ResponseDto<Object> reCreateUserReq(@RequestBody @Valid UserReqCreate userReqCreate) {
        return userReqService.reCreateUserReq(userReqCreate);
    }

    @GetMapping("/findApprvByLoginInfo")
    @ResponseBody
    public ResponseDto<UserReq> findApprvByLoginInfo(UserReqApprvSearch userReqApprvSearch) {
        return userReqService.findApprvByLoginInfo(userReqApprvSearch);
    }
    @GetMapping("/findApprvByUserInfo")
    @ResponseBody
    public ResponseDto<UserReq> findApprvByUserInfo(UserReqApprvSearch userReqApprvSearch) {
        return userReqService.findApprvByUserInfo(userReqApprvSearch);
    }
}
