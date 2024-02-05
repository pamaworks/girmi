package com.girmi.jwt.apis.user;

import com.girmi.jwt.clients.data.jpa.JpaUserClient;
import com.girmi.jwt.models.UserAuthority;
import com.girmi.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    JpaUserClient jpaUserClient;

    public ResponseEntity<Boolean> signup(String userId, String userNm, String userPw, String userEmail) throws Exception {

        if(jpaUserClient.user(userId) != null) {
            return new ResponseEntity<>(false, HttpStatus.ALREADY_REPORTED);
        }

        try {
            User user = new User();
            user.setUserId(userId);
            user.setUserNm(userNm);
            user.setUserPw(passwordEncoder.encode(userPw));
            user.setUserEmail(userEmail);

            jpaUserClient.user(user);

            List<UserAuthority> userAuthorityList = new ArrayList<>();
            UserAuthority userAuthority = new UserAuthority();
            userAuthority.setUserId(userId);
            userAuthority.setAuthority("USER");
            userAuthorityList.add(userAuthority);

            jpaUserClient.userAuthorities(userAuthorityList);

            return new ResponseEntity<>(true, HttpStatus.OK);

        }catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
