package com.example.sistemaestoqueprecorabbitmq.dtos;

import java.io.Serializable;

public record EstoqueDto(String codigoProduto, int quantidade) implements Serializable {
}
