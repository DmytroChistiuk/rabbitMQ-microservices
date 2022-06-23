package com.example.transactionapp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private RabbitTemplate template;

    @GetMapping(value = "/create")
    public void createTransaction(@RequestBody TransactionDTO transaction) throws InterruptedException {
        writeTransaction(transaction);
    }

    private ResponseEntity writeTransaction(TransactionDTO transaction) throws InterruptedException {
    String start = "Transaction with id = " + transaction.getId() + " is processing ...\n";
    String end = "Transaction " + transaction.getName() + " ended.\n" +
            transaction.getMoney() + "$ was successfully added to " + transaction.getAccount() + " account!";
        System.out.println(start);
        Thread.sleep(5000);
        System.out.println(end);
        template.setExchange("exchange");
        template.convertAndSend(transaction.getId());
        return ResponseEntity.ok("added to the queue");
    }

}
