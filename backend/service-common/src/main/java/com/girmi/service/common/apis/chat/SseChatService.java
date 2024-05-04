package com.girmi.service.common.apis.chat;

import com.girmi.service.common.components.SseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SseChatService {

  @Autowired
  SseManager sseManager;

  public void connect(String channel) throws Exception {
    sseManager.subscribe(channel);
  }

  public void sendMessage(String channel, Object message) throws Exception {
    if (message.toString().split("\\s+").length > 1) {
      sseManager.sendToStream(channel, message.toString());
    }
    else {
      sseManager.send(channel, message);
    }
  }

}
