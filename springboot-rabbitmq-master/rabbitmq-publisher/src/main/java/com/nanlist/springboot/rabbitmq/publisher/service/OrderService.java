package com.nanlist.springboot.rabbitmq.publisher.service;

import com.nanlist.springboot.rabbitmq.common.domain.Order;

/**
 * @Author nanlist
 * @Email 1026825281@qq.com
 * @Date 2019/1/25 上午9:44
 */
public interface OrderService {

    void createOrder(Order order);

}
