package com.nanlist.springboot.rabbitmq.common.ConnectionUtil.fanout;
import com.nanlist.springboot.rabbitmq.common.ConnectionUtil.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 订阅模式 生产者
 * 订阅模式：一个生产者发送的消息会被多个消费者获取。
 * @author lgn
 * @version 1.0
 * @date 2021/9/17 16:42
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    /**
     * X代表交换机rabbitMQ内部组件,erlang 消息产生者是代码完成,代码的执行效率不高,消息产生者将消息放入交换机,
     * 交换机发布订阅把消息发送到所有消息队列中,对应消息队列的消费者拿到消息进行消费
     * 相关场景:邮件群发,群聊天,广播(广告)
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            //获取连接
            Connection connection = ConnectionUtil.getConnection();
            //从连接中获取一个通道
            Channel channel = connection.createChannel();
            //声明交换机（分发:发布/订阅模式）
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            //发送消息
            for (int i = 0; i < 10; i++)
            {
                String message = "this is user registe message" + i;
                System.out.println("[send]：" + message);
                //发送消息
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("utf-8"));
                Thread.sleep(5 * i);
            }
            channel.close();
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
