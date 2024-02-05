package com.girmi.jwt.apis.authentication;


import com.girmi.jwt.apis.user.UserService;
import com.girmi.jwt.models.RequestUser;
import com.girmi.jwt.models.SignIn;
import com.girmi.jwt.util.EncryptUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.girmi.constants.SignConstant.SESSION_KEY_TOKEN_JWT;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    AuthenticationService authenticationService;

    @Operation(description="사용자 인증", parameters = {
            @Parameter(name = "userId", description = "User Id"),
            @Parameter(name = "userPw", description = "User Password")
    })
    @PostMapping("/signin")
    public ResponseEntity<SignIn> authenticate(HttpServletRequest request,
                                               @RequestParam(value = "userId") String userId,
                                               @RequestParam(value = "userPw") String userPw
                                               ) throws Exception {
        return authenticationService.authenticate(request, userId, userPw);
    }

    @Operation(description = "로그인 여부확인")
    @GetMapping("/isSignInUser")
    public ResponseEntity<Boolean> isSignInUser(HttpServletRequest request) throws Exception {


        return authenticationService.isSignInUser(request);
    }

}
