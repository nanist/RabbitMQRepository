package com.nanlist.springboot.rabbitmq.publisher;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * 服务生产者
 * @Author lgn
 * @Email 1026825281@qq.com
 * @Date 2019/1/24 上午9:48
 */
@SpringBootApplication
@MapperScan("com.nanlist.springboot.rabbitmq.common.mapper")
@Slf4j
public class Application {
    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);

        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Login: \thttp://{}:{}/\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));


        System.out.println("╭フ哇、內誰說↘生活萁拾狠简单濄勒今天鹫是明天⊕_⊙");
    }
}
