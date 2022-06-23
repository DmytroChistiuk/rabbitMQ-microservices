package com.example.transactionapp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@EnableRabbit
@Component
public class ArchiveListener {

    @RabbitListener(queues = "transactionQueue")
    private void receive(int id){
        System.out.println("In archive add transaction with id = "+ id);
    }
}

