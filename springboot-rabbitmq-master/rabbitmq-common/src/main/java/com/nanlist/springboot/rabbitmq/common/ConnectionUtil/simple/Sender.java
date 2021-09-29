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

        //消息内容
        /**
         * 交换机
         * 队列名
         * 其他属性  路由
         * 消息body
         */
        String message = "错的不是我，是这个世界~";
        channel.basicPublish("", QUEUE_NAME,null,message.getBytes());
        System.out.println("[x]Sent '"+message + "'");

        //最后关闭通关和连接
        channel.close();
        connection.close();
    }
}
