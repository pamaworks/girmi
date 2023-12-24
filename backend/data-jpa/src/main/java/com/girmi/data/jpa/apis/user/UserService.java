package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.models.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserVO getUser(String userId) throws Exception {
        return userRepository.findByUserId(userId);
    }

    public void insertUser(UserVO user) throws Exception {
        userRepository.save(user);
    }

}
