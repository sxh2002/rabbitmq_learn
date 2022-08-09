package com.wwh.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static final String queue_name = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.127.111");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag,message) -> {
            System.out.println(new String(message.getBody()));
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费中断");
        };

        channel.basicConsume(queue_name,true,deliverCallback,cancelCallback);
    }
}
