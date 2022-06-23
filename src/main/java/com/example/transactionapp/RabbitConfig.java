package com.example.transactionapp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory("localhost");
    }
    @Bean
    AmqpAdmin amqpAdmin(){
        return new RabbitAdmin(connectionFactory());
    }
    @Bean
    RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }
    @Bean
    Queue transactionQueue(){
        return new Queue("transactionQueue");
    }
    @Bean
    Queue smsQueue(){
        return new Queue("smsQueue");
    }
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange");
    }
    @Bean
    Binding binding1(){
        return BindingBuilder.bind(transactionQueue()).to(fanoutExchange());
    }
    @Bean
    Binding binding2(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

}
