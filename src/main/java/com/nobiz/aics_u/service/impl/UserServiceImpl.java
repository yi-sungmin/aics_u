package com.nobiz.aics_u.service.impl;

import com.nobiz.aics_u.exception.CustomRuntimeException;
import com.nobiz.aics_u.exception.NoRowsAffectedException;
import com.nobiz.aics_u.model.dto.ResponseDto;
import com.nobiz.aics_u.model.dto.user.User;
import com.nobiz.aics_u.model.dto.user.UserPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserTempPasswdUpdate;
import com.nobiz.aics_u.model.dto.user.UserRegSearch;
import com.nobiz.aics_u.model.dto.userReq.UserReq;
import com.nobiz.aics_u.repository.mapper.UserMapper;
import com.nobiz.aics_u.service.UserService;
import com.nobiz.aics_u.utils.MessageSourceUtil;
import com.nobiz.aics_u.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final MessageSourceUtil ms;

    @Override
    public User addUser(User memberVo) {
        log.info("before create Member - {}", memberVo.toString());
        log.info("After create Member - {}", memberVo.toString());
        return memberVo;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public User modifyUser(User memberVo) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> isCheckedId(String userId) {
        boolean isChecked = userMapper.selectExistsUserId(userId);
        String message = !isChecked ? ms.getMessage("available.id") : ms.getMessage("unavailable.id");
        return new ResponseDto<>(!isChecked, message);
    }

    @Override
    public ResponseDto<String> findGeneratePasswd() {
        return new ResponseDto<>(PasswordUtil.generatePassword(10), ms.getMessage("request.ok"));
    }

    @Override
    public ResponseDto<Object> modifyTempPasswd(UserTempPasswdUpdate userTempPasswdUpdate) {
        userTempPasswdUpdate.setPasswd(PasswordUtil.encodePassword(userTempPasswdUpdate.getPasswd(), passwordEncoder));
        int result = userMapper.updateTempPasswd(userTempPasswdUpdate);
        if (result == 0) {
            throw new NoRowsAffectedException(ms.getMessage("affect.update.fail"));
        }
        return new ResponseDto<>(userTempPasswdUpdate, ms.getMessage("temp.passwd.update.ok"));
    }

    @Override
    public ResponseDto<UserRegSearch> findUserIdAndStts(UserRegSearch userRegSearch) {
        UserRegSearch user = userMapper.selectUserIdAndStts(userRegSearch);
        return new ResponseDto<>(user, ms.getMessage("request.ok"));
    }

    @Override
    public ResponseDto<Object> modifyPasswd(UserPasswdUpdate userPasswdUpdate) {
        Optional<User> userOptional = userMapper.selectUserById(userPasswdUpdate.getUserId());
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(ms.getMessage("not.found.user"));
        }
        /* 비밀번호 매칭 확인 */
        User user = userOptional.get();
        boolean isMatch = passwordEncoder.matches(userPasswdUpdate.getPasswd(), user.getPasswd());
        if (! isMatch) {
            throw new UsernameNotFoundException(ms.getMessage("passwd.not.match"));
        }

        /* 이용자 새 비밀번호 encode */
        userPasswdUpdate.setNewPasswd(PasswordUtil.encodePassword(userPasswdUpdate.getNewPasswd(), passwordEncoder));
        int result = userMapper.updatePasswd(userPasswdUpdate);
        if (result == 0) {
            throw new NoRowsAffectedException(ms.getMessage("affect.update.fail"));
        }

        return new ResponseDto<>(null, ms.getMessage("passwd.update.ok"));
    }

}
