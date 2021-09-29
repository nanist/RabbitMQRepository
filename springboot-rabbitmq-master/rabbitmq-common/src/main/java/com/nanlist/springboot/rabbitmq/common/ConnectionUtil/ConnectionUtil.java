package com.nanlist.springboot.rabbitmq.common.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 编写连接工具类
 */
public class ConnectionUtil {

    /**
     * 这里说一下配置虚拟主机和用户名密码
     *
     * 为什么会有虚拟主机：
     *
     * 当我们在创建用户时，会指定用户能访问一个虚拟机，并且该用户只能访问该虚拟机下的队列和交换机，如果没有指定，默认的是”/”;
     * 一个rabbitmq服务器上可以运行多个vhost，以便于适用不同的业务需要，这样做既可以满足权限配置的要求，
     * 也可以避免不同业务之间队列、交换机的命名冲突问题，因为不同vhost之间是隔离的。
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.8.144");
        //连接5672端口  注意15672为工具界面端口  25672为集群端口
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("chaowei");
        factory.setPassword("123456");
        //获取连接
        Connection connection = factory.newConnection();

        return connection;

    }

}
