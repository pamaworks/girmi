package com.girmi.jwt.apis.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.girmi.jwt.clients.data.jpa.JpaUserClient;
import com.girmi.jwt.config.token.TokenProvider;
import com.girmi.jwt.filter.JwtFilter;
import com.girmi.jwt.models.SignIn;
import com.girmi.jwt.models.UserAll;
import com.girmi.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.girmi.constants.SignConstant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    JpaUserClient jpaUserClient;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public ResponseEntity<SignIn> authenticate(HttpServletRequest request, String userId, String userPw) throws Exception {

        UserAll userInfo = jpaUserClient.user(userId);
        
        if (userInfo == null) {
            return new ResponseEntity<SignIn>(null, null,  HttpStatus.NOT_FOUND);
        }

        try {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId, userPw);

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            User user = new User();
            user.setUserNm(userInfo.getUserNm());

            redisTemplate.opsForValue().set(jwt, new ObjectMapper().writeValueAsString(user));

            return new ResponseEntity<SignIn>(new SignIn(user, jwt), httpHeaders, HttpStatus.OK);

        }catch (Exception ee) {
            log.error(ExceptionUtils.getStackTrace(ee));
            return new ResponseEntity<SignIn>(null, null,  HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<Boolean> isSignInUser(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        String token = request.getHeader(SESSION_KEY_TOKEN_JWT);

        log.info(session.getId());
        log.info("token : {}" , token);

        try {

            if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }
            return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
        }catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
        }

    }

    public ResponseEntity<User> userInfo(HttpServletRequest request) throws Exception {
        try {
            String token = request.getHeader(SESSION_KEY_TOKEN_JWT);
            String userInfoStr = redisTemplate.opsForValue().get(token);

            if (!StringUtils.hasText(token) && !StringUtils.hasText(userInfoStr)
                    && tokenProvider.validateToken(token)) {
                return new ResponseEntity<User>(null, null,  HttpStatus.NOT_FOUND);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(userInfoStr, User.class);
            return new ResponseEntity<User>(user, null,  HttpStatus.OK);
        }catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<User>(null, null,  HttpStatus.NOT_FOUND);
        }
    }

}
