package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.models.User;
import com.girmi.data.jpa.models.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthorityRepository userAuthorityRepository;

    public User getUser(String userId) throws Exception {
        return userRepository.findByUserId(userId);
    }

    public User getUserByPassWd(String userId, String userPw) throws Exception {
        return userRepository.findByUserNotUserPw(userId, userPw);
    }

    public void insertUser(User user) throws Exception {
        userRepository.save(user);
    }

    public void insertUserAuthorities(List<UserAuthority> userAuthorityList) throws Exception {
        userAuthorityRepository.saveAll(userAuthorityList);
    }

    public List<String> getUserAuthorities(String userId) throws Exception {
        return userAuthorityRepository.findById(userId).stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
    }

}
