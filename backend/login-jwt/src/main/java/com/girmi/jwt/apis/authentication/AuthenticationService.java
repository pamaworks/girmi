package com.girmi.jwt.apis.authentication;

import com.girmi.jwt.config.token.TokenProvider;
import com.girmi.jwt.filter.JwtFilter;
import com.girmi.models.LoginVO;
import com.girmi.models.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    TokenProvider tokenProvider;
    AuthenticationManagerBuilder authenticationManagerBuilder;

    public ResponseEntity<LoginVO> authenticate(UserVO user) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<LoginVO>(new LoginVO(user, jwt), httpHeaders, HttpStatus.OK);
    }

}
