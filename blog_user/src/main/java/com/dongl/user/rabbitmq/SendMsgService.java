package com.dongl.user.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Description: 送消息统一业务层
 * @author: YaoGuangXun
 * @date: 2020/3/1821:22
 * @Version: 1.0
 *
 * <p>
 * 说明: 继承RabbitTemplate.ConfirmCallback接口，而ConfirmCallback接口是用来回调消息发送成功后的方法，
 * 当一个消息被成功写入到RabbitMQ服务端时，会自动的回调RabbitTemplate.ConfirmCallback接口内的confirm方法完成通知
 */
public interface SendMsgService extends RabbitTemplate.ConfirmCallback {

    /**
     * @Description: 发送消息方法
     * @Author: YaoGuangXun
     * @Date: 2020/3/18 21:24
     **/
    void sendMessage(Object message);

}
