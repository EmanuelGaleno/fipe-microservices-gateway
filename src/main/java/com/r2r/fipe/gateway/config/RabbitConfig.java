package com.r2r.fipe.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.rabbit.exchange:fipe.exchange}")
    private String exchangeName;

    @Value("${app.rabbit.queue.brands:fipe.brands}")
    private String brandsQueueName;

    @Value("${app.rabbit.routing.brands:brands}")
    private String brandsRoutingKey;

    @Bean
    public DirectExchange fipeExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Queue brandsQueue() {
        return QueueBuilder.durable(brandsQueueName).build();
    }

    @Bean
    public Binding brandsBinding() {
        return BindingBuilder.bind(brandsQueue())
                .to(fipeExchange())
                .with(brandsRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper om) {
        return new Jackson2JsonMessageConverter(om);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf, Jackson2JsonMessageConverter conv) {
        RabbitTemplate tpl = new RabbitTemplate(cf);
        tpl.setMessageConverter(conv);
        return tpl;
    }
}