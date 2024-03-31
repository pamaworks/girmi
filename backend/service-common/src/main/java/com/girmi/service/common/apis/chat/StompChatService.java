package com.girmi.service.common.apis.chat;

import com.girmi.service.common.models.chat.stomp.ChatRoom;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
        for (ChatRoom room: result) {
            List<String> userList = roomUserList.get(room.getRoomId());
            room.setUserCnt(userList == null ? 0 : userList.size());
        }
        Collections.reverse(result);

        return result;
    }

    public List<String> getUserList(String roomId) throws Exception {
        return roomUserList.get(roomId);
    }

    public void addUser(String roomId, String user) throws Exception {
        List<String> userList = roomUserList.get(roomId);
        if(userList == null) {
            userList = new ArrayList<>();
        }
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
        List<String> userList = getUserList(roomId);
        boolean isExist = userList == null ? false : userList.contains(user);
        return isExist;
    }

}
