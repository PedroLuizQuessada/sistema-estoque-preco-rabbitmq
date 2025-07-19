package com.example.sistemaestoqueprecorabbitmq.controllers;

import com.example.sistemaestoqueprecorabbitmq.services.RabbitMQService;
import constants.RabbitMQConstants;
import dtos.EstoqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity<Void> alterarEstoque(@RequestBody EstoqueDto estoqueDto) {
        rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_ESTOQUE_NOME, estoqueDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
