package com.girmi.data.jpa.apis.user;

import com.girmi.data.jpa.models.UserAuthority;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, String>{
}
