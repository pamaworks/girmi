package com.girmi.jwt.apis.authentication;


import com.girmi.jwt.apis.user.UserService;
import com.girmi.jwt.models.RequestUser;
import com.girmi.jwt.models.SignIn;
import com.girmi.jwt.util.EncryptUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
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
    public ResponseEntity<SignIn> authenticate(HttpServletRequest request,
                                               @Valid @RequestBody RequestUser user) throws Exception {

        return authenticationService.authenticate(request, user);
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
