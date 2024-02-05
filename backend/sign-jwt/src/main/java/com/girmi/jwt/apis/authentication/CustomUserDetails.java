package com.girmi.jwt.apis.authentication;

import com.girmi.jwt.clients.data.jpa.JpaUserClient;
import com.girmi.jwt.models.UserAll;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    JpaUserClient jpaUserClient;

    @Override
    @Transactional
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public UserDetails loadUserByUsername(final String username) {

        try {
            UserAll user =  jpaUserClient.user(username);

            if(user != null) {
                List<SimpleGrantedAuthority> authList = new ArrayList<>();
                jpaUserClient.userAuthorities(username).forEach(data -> authList.add(new SimpleGrantedAuthority(data)));
                user.setAuthorities(authList);
            }
            return user;

        }catch (Exception e) {
            return null;
        }
    }
}
