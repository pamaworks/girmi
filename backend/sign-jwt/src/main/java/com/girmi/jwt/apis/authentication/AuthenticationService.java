package com.girmi.jwt.apis.authentication;

import com.girmi.jwt.clients.data.jpa.JpaUserClient;
import com.girmi.jwt.config.token.TokenProvider;
import com.girmi.jwt.filter.JwtFilter;
import com.girmi.jwt.models.RequestUser;
import com.girmi.jwt.models.SignIn;
import com.girmi.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    JpaUserClient jpaUserClient;

    public ResponseEntity<SignIn> authenticate(HttpServletRequest request, RequestUser user) throws Exception {

        User userInfo = jpaUserClient.getUserByPassWd(user.getUserId(), user.getUserPw());
        
        if (userInfo == null) {
            return new ResponseEntity<SignIn>(null, null,  HttpStatus.NOT_FOUND);
        }

        try {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            HttpSession session = request.getSession();
            session.setAttribute("jwt-token", jwt);
            session.setMaxInactiveInterval(30);
            log.info("sessionId : {}", session.getId());
            log.info("jwt-token : {}", session.getAttribute("jwt-token"));

            return new ResponseEntity<SignIn>(new SignIn(userInfo, jwt), httpHeaders, HttpStatus.OK);

        }catch (Exception ee) {
            log.error(ExceptionUtils.getStackTrace(ee));
            return new ResponseEntity<SignIn>(null, null,  HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
