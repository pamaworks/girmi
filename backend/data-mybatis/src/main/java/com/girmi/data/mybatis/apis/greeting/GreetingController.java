package com.girmi.data.mybatis.apis.greeting;

import com.girmi.models.GreetingVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @Operation(description = "test")
    @GetMapping("/getGreeting")
    public List<GreetingVO> getGreeting() throws Exception {
        return greetingService.getGreeting();
    }

}
