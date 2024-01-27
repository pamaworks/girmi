package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String userId) throws Exception {
        return userRepository.findByUserId(userId);
    }

    public User getUserByPassWd(String userId, String userPw) throws Exception {
        return userRepository.findByUserNotUserPw(userId, userPw);
    }

    public void insertUser(User user) throws Exception {
        userRepository.save(user);
    }

}
