package com.girmi.service.common.models.chat;

import com.girmi.service.common.apis.chat.ChatService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
public class ChatRoom {
    private String roomId;
    private String roomNm;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String roomNm) {
        this.roomId = roomId;
        this.roomNm = roomNm;
    }

    public void handleAction(WebSocketSession session, Chat chat, ChatService chatService) {
        if (chat.getMsgType().equals(Chat.MsgType.ENTER)) {
            sessions.add(session);
            chat.setMsg(chat.getSender()+" 입장");
            sendMessage(chat, chatService);
        } else if (chat.getMsgType().equals(Chat.MsgType.TALK)) {
            chat.setMsg(chat.getMsg());
            sendMessage(chat, chatService);
        }
        else {
            try {
                session.close(CloseStatus.NORMAL);
                sessions.remove(session);
                chat.setMsg(chat.getSender()+" 나감");
                sendMessage(chat, chatService);
            }catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }

        }
    }

    public <T> void sendMessage(T message, ChatService service) {
        sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
    }

}
