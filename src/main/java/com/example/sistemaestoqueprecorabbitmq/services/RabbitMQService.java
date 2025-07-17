package com.example.sistemaestoqueprecorabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviaMensagem(String nomeFile, Object mensagem) {
        this.rabbitTemplate.convertAndSend(nomeFile, mensagem);
    }
}
