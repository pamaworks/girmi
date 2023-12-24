package com.girmi.data.mybatis.apis.greeting;


import com.girmi.models.GreetingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GreetingMapper {

    public List<GreetingVO> getGreeting() throws Exception;
}
