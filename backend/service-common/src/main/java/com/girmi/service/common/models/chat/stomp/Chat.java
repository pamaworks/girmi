package com.girmi.service.common.models.chat.stomp;

import lombok.Data;

@Data
public class Chat {
    private String roomId;
    private String sender;
    private String message;
}
