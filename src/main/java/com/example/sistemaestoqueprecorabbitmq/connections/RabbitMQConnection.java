package com.example.sistemaestoqueprecorabbitmq.connections;

import constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    @Value("${rabbitmq.exchange.nome}")
    private String nomeExchange;

    private final AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(nomeExchange);
    }

    private Binding relacionamento(Queue fila, DirectExchange trocaDireta) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, trocaDireta.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaEstoque = this.fila(RabbitMQConstants.FILA_ESTOQUE_NOME);
        Queue filaPreco = this.fila(RabbitMQConstants.FILA_PRECO_NOME);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = this.relacionamento(filaPreco, troca);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
