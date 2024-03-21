package com.girmi.service.common.utils.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.girmi.service.common.apis.chat.ChatService;
import com.girmi.service.common.models.chat.Chat;
import com.girmi.service.common.models.chat.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;

    private final ChatService service;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);


        Chat chatMessage = mapper.readValue(payload, Chat.class);
        log.info("session {}", chatMessage.toString());

        ChatRoom room = service.findRoomById(chatMessage.getRoomId());
        log.info("room {}", room.toString());

        room.handleAction(session, chatMessage, service);
    }

}
