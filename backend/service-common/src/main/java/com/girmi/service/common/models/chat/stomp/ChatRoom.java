package com.girmi.service.common.models.chat.stomp;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatRoom {
    private String roomId;
    private String roomNm;

    public static ChatRoom create(String name){
        ChatRoom room = new ChatRoom();

        room.roomId = UUID.randomUUID().toString();
        room.roomNm = name;
        return room;
    }
}
