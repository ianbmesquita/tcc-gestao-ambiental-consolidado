package br.pucmg.sigam.monitoramento.application.domain.incidente.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
    private static final String ROUTING_KEY_QUEUE = "sigam-notificacao-incidentes";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(final String message) {
        rabbitTemplate.convertAndSend(ROUTING_KEY_QUEUE, message);
    }
}
