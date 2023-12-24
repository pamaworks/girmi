package com.girmi.service.common.apis.greeting;

import com.girmi.models.GreetingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
    @Autowired
    GreetingClient greetingClient;

    public List<GreetingVO> getGreeting() throws Exception {
        return greetingClient.getGreeting();
    }

}
