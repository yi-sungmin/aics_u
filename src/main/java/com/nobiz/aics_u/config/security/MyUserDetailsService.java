package com.nobiz.aics_u.config.security;

import com.nobiz.aics_u.model.dto.user.User;
import com.nobiz.aics_u.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userMapper.selectUserById(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("없는 사용자 입니다."));
    }
}
