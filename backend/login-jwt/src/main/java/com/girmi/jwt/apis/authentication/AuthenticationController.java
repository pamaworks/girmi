package com.girmi.jwt.apis.authentication;


import com.girmi.jwt.apis.user.UserService;
import com.girmi.jwt.config.token.TokenProvider;
import com.girmi.jwt.filter.JwtFilter;
import com.girmi.jwt.util.EncryptUtil;
import com.girmi.models.LoginVO;
import com.girmi.models.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {



    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Operation(description="사용자 인증")
    @PostMapping("/login")
    public ResponseEntity<LoginVO> authenticate(@Valid @RequestBody UserVO user) throws Exception {

        return authenticationService.authenticate(user);
    }

    @Operation(description = "암호화 된 패스워드 받기")
    @GetMapping("/encrypt-password")
    public ResponseEntity<String> userPwEncrpt(@RequestParam(value = "passwd") String passwd) throws Exception {
        return new ResponseEntity<String>(userService.getEncryptPassWd(passwd), HttpStatus.OK);
    }

    @Operation(description = "암호화 키 받기")
    @GetMapping("/encrypt-key")
    public ResponseEntity<String> encryptKey() throws Exception {
        return new ResponseEntity<>(EncryptUtil.sha256Key, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> isLoginUser() throws Exception {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
