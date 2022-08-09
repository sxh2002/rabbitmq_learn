package com.wwh.rabbitmq.two;

import com.rabbitmq.client.Channel;
import com.wwh.rabbitmq.utils.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class MyTask {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String str = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,str.getBytes(StandardCharsets.UTF_8));
            System.out.println("消息发送成功:"+str);
        }
    }
}
