package com.dongl.user.rabbitmq;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/18 21:32
 * @Version: 1.0
 */
public class RabbitMQConstants {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE = "checkcode.exchange";
    /**
     * 队列名称
     */
    public static final String QUEUE_ROUTE_NAME = "checkcode.queue";
    // 队列路由键
    public static final String QUEUE_ROUTE_KEY = "checkcode.route";
}
