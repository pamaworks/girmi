package com.girmi.data.jpa.apis.greeting;

import com.girmi.models.GreetingVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @Operation(description = "search greeting")
    @GetMapping("/getGreeting")
    public Iterable<GreetingVO> getGreeting() throws Exception {
        return greetingService.getGreeting();
    }

    @Operation(description = "insert greeting")
    @PostMapping("/insertGreeting")
    @Schema(name = "greetingVO")
    public void insertGreeting(
             GreetingVO greetingVO
    ) throws Exception {
        greetingService.insertGreeting(greetingVO);
    }

    @Operation(description = "delete greeting")
    @GetMapping("/deleteGreeting")
    public void insertGreeting(
        @RequestParam(value = "id") String id
    ) throws Exception {
        greetingService.deleteGreeting(id);
    }

    @Operation(description = "search data by code")
    @GetMapping("/getDataByCode")
    public List<GreetingVO> getDataByCode(@RequestParam(value = "code")String code) throws Exception{
        return greetingService.getDateByCode(code);
    }


}
