package com.dongl.user.config;

import com.dongl.user.rabbitmq.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ 的交换机和队列配置
 * @author: YaoGuangXun
 * @date: 2020/3/18 21:08
 * @Version: 1.0
 */
@Configuration
public class RabbitMQConfig {


    /**
     * 配置交换机实例
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConstants.EXCHANGE);
    }
    /**
     * 配置队列实例，并且设置持久化队列
     * @return
     */

    @Bean
    public Queue queue() {
        return new Queue(RabbitMQConstants.QUEUE_ROUTE_NAME,true);
    }

    /**
     * 将队列绑定到交换机上，并设置消息分发的路由键
     *
     * @return
     */
    @Bean
    public Binding binding() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(queue()).to(directExchange()).with(RabbitMQConstants.QUEUE_ROUTE_KEY);
    }

}
