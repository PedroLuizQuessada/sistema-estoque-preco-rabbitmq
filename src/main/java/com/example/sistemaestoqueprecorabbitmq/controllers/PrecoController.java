package com.example.sistemaestoqueprecorabbitmq.controllers;

import com.example.sistemaestoqueprecorabbitmq.services.RabbitMQService;
import constants.RabbitMQConstants;
import dtos.PrecoDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping
    private ResponseEntity<Void> alterarPreco(@RequestBody PrecoDto precoDto) {
        rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_PRECO_NOME, precoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
