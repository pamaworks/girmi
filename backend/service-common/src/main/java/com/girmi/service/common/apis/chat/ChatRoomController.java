package com.girmi.service.common.apis.chat;

import com.girmi.service.common.models.chat.stomp.ChatRoom;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/room")
public class ChatRoomController {

    @Autowired
    StompChatService stompChatService;

    @Operation(description = "채팅방리스트 조회")
    @GetMapping("/list")
    public List<ChatRoom> getRoomList() {
        return stompChatService.findAllRooms();
    }

    @Operation(description = "채팅방 개설")
    @PutMapping("/room")
    public ChatRoom addRoom(@RequestParam(value = "roomNm") String roomNm) throws Exception {
        return stompChatService.createChatRoom(roomNm);
    }

    @Operation(description = "사용자있는지 확인")
    @GetMapping("/room/{roomId}/{user}")
    public Boolean isExistUser(@PathVariable(value = "roomId") String roomId, @PathVariable(value = "user") String user) throws Exception {
        return stompChatService.isExistUser(roomId, user);
    }
}
