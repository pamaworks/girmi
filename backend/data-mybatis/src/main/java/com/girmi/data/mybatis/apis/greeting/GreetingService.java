package com.girmi.data.mybatis.apis.greeting;

import com.girmi.models.GreetingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
    @Autowired
    GreetingMapper greetingMapper;

    public List<GreetingVO> getGreeting() throws Exception {
        return greetingMapper.getGreeting();
    }

}
