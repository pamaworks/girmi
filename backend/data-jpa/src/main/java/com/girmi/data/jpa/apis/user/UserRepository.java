package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.common.CommonRepository;
import com.girmi.data.jpa.models.UserVO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserVO, String>, CommonRepository<UserVO, String> {

    public UserVO findByUserId(String userId) throws Exception;
}
