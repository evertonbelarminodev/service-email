package com.micro.service.email.listener;

import com.micro.service.email.model.Client;
import com.micro.service.email.tools.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${queue}")
    public void receiveClient(Client client) {

        emailService.sendEmail(client.getEmail());
        logger.info("Email enviado para: "+client.getEmail());
    }
}
