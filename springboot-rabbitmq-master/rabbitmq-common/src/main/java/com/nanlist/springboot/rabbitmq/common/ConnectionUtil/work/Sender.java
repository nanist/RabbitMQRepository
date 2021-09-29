package com.nanlist.springboot.rabbitmq.common.ConnectionUtil.work;

import com.nanlist.springboot.rabbitmq.common.ConnectionUtil.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * work模式 生产者
 * @author lgn
 * @version 1.0
 * @date 2021/9/17 15:18
 */
public class Sender {

    private final  static String QUEUE_NAME = "queue_work";

    /**
     * work模式：一个生产者，多个消费者，每个消费者获取到的消息唯一。
     * 消息产生者将消息放入队列消费者可以有多个,消费者1,消费者2,同时监听同一个队列,消息被消费?
     * C1 C2共同争抢当前的消息队列内容,谁先拿到谁负责消费消息(隐患,高并发情况下,默认会产生某一个消息被多个消费者共同使用,
     * 可以设置一个开关(syncronize,与同步锁的性能不一样) 保证一条消息只能被一个消费者使用)
     * 应用场景:红包;大项目中的资源调度(任务分配系统不需知道哪一个任务执行系统在空闲,直接将任务扔到消息队列中,
     * 空闲的系统自动争抢)
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for(int i = 0; i < 100; i++){
            String message = "周璐老师是个暴躁老哥" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] Sent '"+message + "'");
            Thread.sleep(i*10);
        }

        channel.close();
        connection.close();
    }
}
