package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.common.CommonRepository;
import com.girmi.data.jpa.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String>, CommonRepository<User, String> {

    public User findByUserId(String userId) throws Exception;

    @Query(nativeQuery = true)
    public User findByUserNotUserPw(@Param("userId") String userId, @Param("userPw")String userPw) throws Exception;

}
