package com.girmi.jwt.clients.data.jpa;

import com.girmi.jwt.models.UserAll;
import com.girmi.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "jpa.user", url = "${clients.data.jpa.user}")
public interface JpaUserClient {

    @GetMapping(value = "/userByUserPasswd")
    public User getUserByPassWd(@RequestParam(value = "userId") String userId, @RequestParam(value = "userPw") String userPw) throws Exception;

    @GetMapping(value = "/user")
    public UserAll user(@RequestParam(value = "") String userId) throws Exception;
 }
