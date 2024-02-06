package com.girmi.jwt.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;

@Configuration
@EnableRedisHttpSession(
         redisNamespace = "${spring.session.redis.namespace}"
)
public class SessionConfig {

    @Value("${spring.session.timeout}")
    int maxInactiveIntervalInSeconds;

    @Autowired
    RedisSessionRepository redisSessionRepository;

    @PostConstruct
    public void redisSessionRepositoryAfterSet() {
        redisSessionRepository.setDefaultMaxInactiveInterval(Duration.ofSeconds(maxInactiveIntervalInSeconds));
    }

}
