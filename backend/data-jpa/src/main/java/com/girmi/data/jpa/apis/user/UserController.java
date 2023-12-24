package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.models.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public UserVO user(String userId) throws Exception {
        return userService.getUser(userId);
    }

    @PostMapping("/user")
    public void user(@RequestBody UserVO user) throws Exception{
        userService.insertUser(user);
    }

}
