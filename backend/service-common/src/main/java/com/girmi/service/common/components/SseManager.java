package com.girmi.service.common.components;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SseManager {

  private ScheduledExecutorService executorService = null;
  private Map<String, Disposable> subscriptions = null;

  @Value("${spring.sse.thread.pool-size}")
  private int corePoolSize;

  private String MSG_UNSUBSCRIVE = ":unsubscribe:";
  private String MSG_UNSUBSCRIVE_ALL = ":unsubscribe:";

  @PostConstruct
  public void init() {
    executorService = Executors.newScheduledThreadPool(corePoolSize);
    subscriptions = new ConcurrentHashMap<>();
  }

  private final ReactiveRedisTemplate<String, Object> redisTemplate;

  public void subscribe(String channel) throws Exception {
    Disposable disposable = redisTemplate.listenTo(ChannelTopic.of(channel))
        .map(ReactiveSubscription.Message::getMessage).subscribe(
            message -> controlMessage(message, channel)
        );
    subscriptions.put(channel, disposable);
  }

  public void controlMessage(Object message, String channel) {
    if (message.equals(MSG_UNSUBSCRIVE)) {
      unsubscribeFromChannel(channel);
    }

    if(message.equals(MSG_UNSUBSCRIVE_ALL)) {
      unsubscribeAllChannel();
    }
  }

  public Mono<Object> publish(String channel, Object message) throws Exception {
    return redisTemplate.convertAndSend(channel, message).map(aLong -> message);
  }

  public void send(String channel, Object message) throws Exception {
    publish(channel, message).subscribe();
  }

  public void sendToStream(String channel, String message) throws Exception {
    String[] words = message.split("\\s+");
    int[] index = {0};
    executorService.scheduleAtFixedRate(() -> {
      try {
        if (index[0] < words.length) {
          send(channel, words[index[0]++]); // 다음 단어 전송
        } else {
          send(channel, ":end:");
        }
      } catch (Exception e) {

      }
    }, 0, 100, TimeUnit.MILLISECONDS);
  }

  public void unsubscribeFromChannel(String channelName) {
    Disposable subscription = subscriptions.get(channelName);
    if (subscription != null && !subscription.isDisposed()) {
      subscription.dispose();
      subscriptions.remove(channelName);
    }
  }

  public void unsubscribeAllChannel() {
      subscriptions.values().forEach(Disposable::dispose);
      subscriptions.clear();
  }

}
