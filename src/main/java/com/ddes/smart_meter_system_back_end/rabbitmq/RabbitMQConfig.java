package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RabbitMQConfig {
    Logger log = Logger.getLogger(RabbitMQConfig.class.getName());

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        log.info("RabbitAdmin created.");
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        log.info("RabbitTemplate created.");
        return new RabbitTemplate(connectionFactory);
    }

   @Bean
    DirectExchange directExchange() {
        log.info("Bills exchange created.");
        return new DirectExchange("server.bills");
    }
    
    @Bean
    Queue queue() {
        log.info("Readings queue created.");
        return new Queue("server.readings", true, false, false);
    }
}