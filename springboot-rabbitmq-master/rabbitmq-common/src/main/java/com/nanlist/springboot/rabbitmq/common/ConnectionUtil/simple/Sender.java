package com.nanlist.springboot.rabbitmq.common.ConnectionUtil.simple;
import com.nanlist.springboot.rabbitmq.common.ConnectionUtil.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <h4>springboot-rabbitmq</h4>
 * <p>简单模式 生产者</p>
 * @author : lgn
 * @date : 2021-09-17 14:54
 **/
public class Sender {
    private final static String QUEUE_NAME = "simple_queue";
    private final static String EXCHANGE_NAME = "simple_exchange";
    /**
     * 最新版本的RabbitMQ有四种交换机类型，分别是Direct exchange（默认）、Fanout exchange、Topic exchange、Headers exchange。
     * Direct Exchange 是 RabbitMQ 默认的 Exchange，完全根据 RoutingKey 来路由消息。设置 Exchange 和 Queue 的 Binding 时需指定
     * RoutingKey（一般为 Queue Name），发消息时也指定一样的 RoutingKey，消息就会被路由到对应的Queue。
     */
    private final static String EXCHANGE_TYPE = "direct";

    /**
     * 简单模式：一个生产者，一个消费者
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        /**
         * 队列名
         * 是否持久化
         *  是否排外  即只允许该channel访问该队列   一般等于true的话用于一个队列只能有一个消费者来消费的场景
         *  是否自动删除  消费完删除
         *  其他属性
         *
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        /**
         * exchange–exchange的名称
         * 类型–交换类型
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);


        //消息内容
        /**
         * 交换机
         * 队列名
         * 其他属性  路由
         * 消息body
         */
        String message = "华晨宇是傻逼~";
        channel.basicPublish("", QUEUE_NAME,null,message.getBytes());
        System.out.println("[x]Sent '"+message + "'");

        //最后关闭通关和连接
        channel.close();
        connection.close();
    }
}
