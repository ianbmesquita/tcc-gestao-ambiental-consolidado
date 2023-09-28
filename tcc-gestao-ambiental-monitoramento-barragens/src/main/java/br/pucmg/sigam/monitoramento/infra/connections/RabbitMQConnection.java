package br.pucmg.sigam.monitoramento.infra.connections;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE_NAME = "amq.direct";
    private static final String QUEUE_NAME = "sigam-notificacao-incidentes";

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue queue(String queueName){
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange directExchange){
        return new Binding(queue.getName(),
                Binding.DestinationType.QUEUE,
                directExchange.getName(),
                queue.getName(),
                null);
    }

    @PostConstruct
    private void createQueue(){
        var queue = this.queue(QUEUE_NAME);
        var directExchange = this.directExchange();
        var binding = this.binding(queue, directExchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareExchange(directExchange);
        this.amqpAdmin.declareBinding(binding);
    }
}
