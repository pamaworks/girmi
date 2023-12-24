package com.girmi.jwt.apis.user;

import com.girmi.jwt.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public String getEncryptPassWd(String passwd) throws Exception {
        return EncryptUtil.getEncryptSha256(passwd);
    }

}
