package com.girmi.service.common.models.chat;

import lombok.Builder;
import lombok.Data;

// https://terianp.tistory.com/142 [Terian의 IT 도전기:티스토리]
@Data
@Builder
public class Chat {

    // 메시지  타입 : 입장, 채팅
    public enum MsgType{
        ENTER, TALK, CLOSE
    }

    private MsgType msgType;
    private String roomId;
    private String sender;
    private String msg;
    private String time;

}
