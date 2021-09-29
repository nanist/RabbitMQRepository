package com.nanlist.springboot.rabbitmq.common.ConnectionUtil.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.nanlist.springboot.rabbitmq.common.ConnectionUtil.ConnectionUtil;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <p>简单模式 消费者</p>
 * @author lgn
 * @version 1.0
 * @date 2021/9/17 14:58
 */
public class Receiver {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while(true){
            //该方法会阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received '"+message+"'");

        }
    }
}
