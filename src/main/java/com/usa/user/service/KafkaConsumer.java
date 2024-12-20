package com.usa.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usa.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "order", groupId = "dev")
    public void updateDeposit(String kafkaMessage) {
        log.info("Kafka Message: -> {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        // DB 아직 구축 안됨
        // DB에서 읽어온걸로 치고
        String email = (String) map.get("email");
        User user = new User(email, "alex", 10000000L);

        long usedAmount = ((Number) map.get("amount")).longValue();

        user.setDeposit(user.getDeposit() - usedAmount);

        log.info("User email: -> {}", user.getEmail());
        log.info("User nickname: -> {}", user.getNickname());
        log.info("User deposit: -> {}", user.getDeposit());


    }
}
