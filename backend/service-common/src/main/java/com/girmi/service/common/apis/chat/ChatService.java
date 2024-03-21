package com.girmi.service.common.apis.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.girmi.service.common.models.chat.ChatRoom;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class ChatService {

    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name) throws Exception {

        try {
            String roomId = UUID.randomUUID().toString();

            ChatRoom room = ChatRoom.builder()
                    .roomId(roomId)
                    .roomNm(name)
                    .build();

            chatRooms.put(roomId, room);
            return room;
        }catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
