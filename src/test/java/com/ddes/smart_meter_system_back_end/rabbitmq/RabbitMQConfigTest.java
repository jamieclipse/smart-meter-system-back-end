package com.ddes.smart_meter_system_back_end.rabbitmq;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class RabbitMQConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @Test
    public void testBillsExchangeBeanExists() {
        DirectExchange billsExchange = (DirectExchange) applicationContext.getBean("billsExchange");
        assertThat(billsExchange).isNotNull();
    }

    @Test
    public void testBillsExchangeProperties() {
        DirectExchange billsExchange = rabbitMQConfig.billsExchange();
        assertThat(billsExchange.getName()).isEqualTo("server.bills");
        assertThat(billsExchange.isDurable()).isTrue();
        assertThat(billsExchange.isAutoDelete()).isFalse();
    }
}