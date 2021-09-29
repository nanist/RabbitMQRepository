package com.nanlist.springboot.rabbitmq.common.ConnectionUtil.work;
import com.nanlist.springboot.rabbitmq.common.ConnectionUtil.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * work 消费者1
 * @author lgn
 * @version 1.0
 * @date 2021/9/17 15:25
 */
public class Receiver1 {
    private final static  String QUEUE_NAME = "queue_work";

    /**
     * 结果：
     *
     * 1、一条消息只会被一个消费者接收；
     *
     * 2、rabbit采用轮询的方式将消息是平均发送给消费者的；
     *
     * 3、消费者在处理完某条消息后，才会收到下一条消息。
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false,false, false,null);
        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        //关于手工确认 待之后有时间研究下
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[消费者1] Received1 '"+message+"'");
            Thread.sleep(10);
            //返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
