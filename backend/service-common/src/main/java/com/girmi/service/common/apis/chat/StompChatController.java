package com.girmi.service.common.apis.chat;

import com.girmi.service.common.models.chat.stomp.Chat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;

    @Autowired
    StompChatService stompChatService;

    @MessageMapping(value = "/chat/enter")
    public void enter(Chat message) throws Exception{
        stompChatService.addUser(message.getRoomId(), message.getSender());
        message.setMessage(message.getSender() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/out")
    public void out(Chat message) throws Exception {
        stompChatService.delUser(message.getRoomId(), message.getSender());
        message.setMessage(message.getSender() + "님이 나가셨습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(Chat message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
