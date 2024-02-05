package com.girmi.jwt.apis.user;

import com.girmi.jwt.models.RequestUser;
import com.girmi.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(description = "Sign Up", parameters = {
            @Parameter(name = "userId", description = "User Id"),
            @Parameter(name = "userNm", description = "User Name"),
            @Parameter(name = "userPw", description = "User Password"),
            @Parameter(name = "userEmail", description = "User EMAIL"),
    })
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "userNm") String userNm,
            @RequestParam(value = "userPw") String userPw,
            @RequestParam(value = "userEmail") String userEmail
    ) throws Exception {
        return userService.signup(userId, userNm, userPw, userEmail);
    }
}
