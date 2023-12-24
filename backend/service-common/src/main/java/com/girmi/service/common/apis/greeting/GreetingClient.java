package com.girmi.service.common.apis.greeting;

import com.girmi.models.GreetingVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.girmi.constants.RestUrlRoot.DATA_MYBATIS;

@FeignClient(url = "${" + DATA_MYBATIS + ".greeting}", name = "test")
public interface GreetingClient {

    @GetMapping("/getGreeting")
    @ResponseBody
    public List<GreetingVO> getGreeting();

}
