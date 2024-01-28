package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.models.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Operation(description = "Search User By UserId")
    @GetMapping("/user")
    public User user(String userId) throws Exception {
        return userService.getUser(userId);
    }

    @Operation(description = "Search User By UserId, Password", parameters = {
            @Parameter(name = "userId", description = "User Id"),
            @Parameter(name = "userPw", description = "User Password")
    })
    @GetMapping(value = "/userByUserPasswd")
    public User user(String userId, String userPw) throws Exception {
        return userService.getUserByPassWd(userId, userPw);
    }

    @PostMapping("/user")
    public void user(@RequestBody User user) throws Exception{
        userService.insertUser(user);
    }

}
