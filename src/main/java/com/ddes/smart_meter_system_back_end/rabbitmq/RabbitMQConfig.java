package com.ddes.smart_meter_system_back_end.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class RabbitMQConfig {
    Logger log = Logger.getLogger(RabbitMQConfig.class.getName());

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        log.info("RabbitTemplate created.");
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    DirectExchange readingsExchange() {
        log.info("Readings exchange created.");
        return new DirectExchange("client.readings");
    }
   
    @Bean
    DirectExchange billsExchange() {
        log.info("Bills exchange created.");
        return new DirectExchange("server.bills");
    }

    @Bean
    FanoutExchange notificationExchange() {
        log.info("Notifications exchange created.");
        return new FanoutExchange("server.notifications");
    }
    
    @Bean
    Queue queue() {
        log.info("Readings queue created.");
        return new Queue("server.readings", true, false, false);
    }

    @Bean
    Binding readingsBinding(Queue queue, DirectExchange readingsExchange) {
        log.info("Binding readings queue to readings exchange.");
        return BindingBuilder.bind(queue).to(readingsExchange).with("readings");
    }
}
