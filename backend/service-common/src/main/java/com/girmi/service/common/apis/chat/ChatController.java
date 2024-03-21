package com.girmi.service.common.apis.chat;

import com.girmi.service.common.models.chat.ChatRoom;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Operation(description = "create chat room")
    @PostMapping("/create/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam(value = "roomNm") String roomNm) throws Exception{
        return chatService.createRoom(roomNm);
    }

    @Operation(description = "find rooms")
    @GetMapping("/find/rooms")
    public List<ChatRoom> findAllRooms(){
        return chatService.findAllRoom();
    }

}
