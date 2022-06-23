package com.example.transactionapp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class SmsListener {
    @RabbitListener(queues = "smsQueue")
    private void sendSms(int id) {
        System.out.println("Transaction was sent by SMS");
    }


}
