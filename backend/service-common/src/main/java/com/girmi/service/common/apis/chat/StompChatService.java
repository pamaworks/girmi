package com.girmi.service.common.apis.chat;

import com.girmi.service.common.models.chat.stomp.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StompChatService {

    private Map<String, ChatRoom> chatRoom;
    private Map<String, List<String>> roomUserList;

    @PostConstruct
    public void init() {
        chatRoom = new LinkedHashMap<String, ChatRoom>();
        roomUserList = new LinkedHashMap<String, List<String>>();
    }

    public List<ChatRoom> findAllRooms(){
        List<ChatRoom> result = new ArrayList<>(chatRoom.values());
        Collections.reverse(result);

        return result;
    }

    public List<String> getUserList(String roomId) throws Exception {
        return roomUserList.get(roomId);
    }

    public void addUser(String roomId, String user) throws Exception {
        List<String> userList = roomUserList.get(roomId);
        userList.add(user);
        roomUserList.put(roomId, userList);
    }

    public void delUser(String roomId, String user) throws Exception {
        List<String> userList = roomUserList.get(roomId).stream().filter(u -> !u.equals(user)).collect(Collectors.toList());
        roomUserList.put(roomId, userList);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom room = ChatRoom.create(name);
        chatRoom.put(room.getRoomId(), room);

        return room;
    }

    public Boolean isExistUser(String roomId, String user) throws Exception{
        return getUserList(roomId).contains(user);
    }

}
