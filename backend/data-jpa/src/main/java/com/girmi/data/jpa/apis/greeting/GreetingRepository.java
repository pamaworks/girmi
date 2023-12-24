package com.girmi.data.jpa.apis.greeting;

import com.girmi.data.jpa.common.CommonRepository;
import com.girmi.models.GreetingVO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GreetingRepository extends CrudRepository<GreetingVO, String>, CommonRepository<GreetingVO, String> {

    List<GreetingVO> findAllByCode(String code) throws Exception;

}
