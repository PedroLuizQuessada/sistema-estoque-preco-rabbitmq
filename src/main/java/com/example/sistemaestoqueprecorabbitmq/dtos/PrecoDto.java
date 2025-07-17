package com.example.sistemaestoqueprecorabbitmq.dtos;

import java.io.Serializable;

public record PrecoDto(String codigoProduto, double preco) implements Serializable {
}
