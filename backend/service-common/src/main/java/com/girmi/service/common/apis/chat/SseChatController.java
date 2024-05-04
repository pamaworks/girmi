package com.girmi.service.common.apis.chat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/sse")
public class SseChatController {

  @Autowired
  SseChatService sseChatService;

  @GetMapping("/connect/{channel}")
  public ResponseEntity<String> connect(@PathVariable(value = "channel") String channel) throws Exception {
    sseChatService.connect(channel);
    return ResponseEntity.ok("SUCCESS");
  }

  @PostMapping("/send/{channel}")
  public ResponseEntity<String> sendMessage(
      @PathVariable(value="channel") String channel,
      @RequestBody String message) throws Exception {

    sseChatService.sendMessage(channel, message);

    return ResponseEntity.ok("SUCCESS");
  }

}
