package com.nanlist.springboot.rabbitmq.common.ConnectionUtil.routing;

import com.nanlist.springboot.rabbitmq.common.ConnectionUtil.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式 生产者
 * 发送消息到交换机并且要指定路由key ，消费者将队列绑定到交换机时需要指定路由key
 * @author lgn
 * @version 1.0
 * @date 2021/9/17 16:04
 */
public class Sender {
    private final static String EXCHANGE_NAME = "exchange_direct";
    private final static String EXCHANGE_TYPE = "direct";

    /**
     * 路由模式：
     *
     * 1、每个消费者监听自己的队列，并且设置routingkey。
     *
     * 2、生产者将消息发给交换机，由交换机根据routingkey来转发消息到指定的队列。
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,EXCHANGE_TYPE);

        String message = "那一定是蓝色";
        channel.basicPublish(EXCHANGE_NAME,"key2", null, message.getBytes());
        System.out.println("[x] Sent '"+message+"'");

        channel.close();
        connection.close();
    }
}
