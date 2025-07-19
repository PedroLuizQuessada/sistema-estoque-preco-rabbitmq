package com.example.sistemaestoqueprecorabbitmq.controllers;

import com.example.libsistemaestoquepreco.dtos.PrecoDto;
import com.example.sistemaestoqueprecorabbitmq.services.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preco")
public class PrecoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Value("${rabbitmq.fila.preco.nome}")
    private String filaPrecoNome;

    @PutMapping
    private ResponseEntity<Void> alterarPreco(@RequestBody PrecoDto precoDto) {
        rabbitMQService.enviaMensagem(filaPrecoNome, precoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
