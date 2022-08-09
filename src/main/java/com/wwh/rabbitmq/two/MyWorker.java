package com.wwh.rabbitmq.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wwh.rabbitmq.utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyWorker {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到消息："+new String(message.getBody()));
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag+"消息消费中断");
        };

        System.out.println("C3等待接受消息");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
