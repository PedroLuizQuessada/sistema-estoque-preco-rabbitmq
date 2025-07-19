package com.example.sistemaestoqueprecorabbitmq.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviaMensagem(String nomeFile, Object mensagem) {
        try {
            this.rabbitTemplate.convertAndSend(nomeFile, objectMapper.writeValueAsString(mensagem));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
