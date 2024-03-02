package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.models.User;
import com.girmi.data.jpa.models.UserAuthority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Operation(description = "Search User By UserId", parameters = {
            @Parameter(name = "userId", description = "User Id")
    })
    @GetMapping(value = "/user")
    public User user(@RequestParam(value = "userId") String userId) throws Exception {
        return userService.getUser(userId);
    }

    @Operation(description = "Search User By UserId, Password", parameters = {
            @Parameter(name = "userId", description = "User Id"),
            @Parameter(name = "userPw", description = "User Password")
    })
    @GetMapping(value = "/userByUserPasswd")
    public User user(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "userPw") String userPw) throws Exception {
        return userService.getUserByPassWd(userId, userPw);
    }

    @Operation(description = "Insert User")
    @PostMapping(value = "/user")
    public void user(@RequestBody User user) throws Exception{
        userService.insertUser(user);
    }

    @Operation(description = "Insert User Authorities")
    @PostMapping(value = "/userAuthorities")
    public void userAuthority(@RequestBody List<UserAuthority> userAuthority) throws Exception{
        userService.insertUserAuthorities(userAuthority);
    }

    @Operation(description = "Get User Authorities", parameters = {
            @Parameter(name = "userId", description = "User Id")
    })
    @GetMapping(value = "/userAuthorities")
    public List<String> userAuthorities(@RequestParam(value = "userId") String userId) throws Exception {
        return userService.getUserAuthorities(userId);
    }

}
