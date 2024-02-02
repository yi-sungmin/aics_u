package com.nobiz.aics_u.controller;

import com.nobiz.aics_u.annotation.Exclude;
import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.user.UserPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserTempPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserRegSearch;
import com.nobiz.aics_u.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    @GetMapping("/isCheckedId")
    @ResponseBody
    public ResponseDto<Boolean> isCheckedId(@RequestParam String userId) {
        return userService.isCheckedId(userId);
    }

    @GetMapping("/generate-passwd")
    @ResponseBody
    public ResponseDto<String> findGeneratePasswd() {
        return userService.findGeneratePasswd();
    }

    @PutMapping("/modifyTempPasswd")
    @Exclude
    @ResponseBody
    public ResponseDto<?> modifyTempPasswd(@Valid @RequestBody UserTempPasswdUpdate userTempPasswdUpdate) {
        return userService.modifyTempPasswd(userTempPasswdUpdate);
    }

    @GetMapping("/findUserIdAndStts")
    @ResponseBody
    public ResponseDto<UserRegSearch> findUserIdAndStts(UserRegSearch userRegSearch) {
        return userService.findUserIdAndStts(userRegSearch);
    }

    @PutMapping("/passwd")
    @ResponseBody
    public ResponseDto<Object> modifyPasswd(@Valid @RequestBody UserPasswdUpdate userPasswdUpdate) {
        return userService.modifyPasswd(userPasswdUpdate);
    }
}
