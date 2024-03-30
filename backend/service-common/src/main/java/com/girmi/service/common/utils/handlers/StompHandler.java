package com.girmi.service.common.utils.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompHandler  implements ChannelInterceptor {

    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("Stomp Hanler 실행");
        return message;
    }
}
