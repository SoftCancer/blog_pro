package com.dongl.user.rabbitmq;

import com.dongl.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Description: RabbitMQ 消息发送及消息确认功能实现
 * @author: YaoGuangXun
 * @date: 2020/3/18 21:24
 * @Version: 1.0
 */
@Component
public class SendMsgServiceImpl implements SendMsgService {

    private static Logger logger = LoggerFactory.getLogger(SendMsgServiceImpl.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Object message) {
        // 设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        // 构建回调返回的数
        CorrelationData correlationData = new CorrelationData(UUIDUtils.getUUID());
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE,RabbitMQConstants.QUEUE_ROUTE_KEY,message,correlationData);
        logger.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: " + message);

    }
    /**
     * 消息回调确认方法
     * @param correlationData 回调数据
     * @param isSendSuccess   是否发送成功
     * @param s 失败消息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String s) {
        logger.info("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (isSendSuccess){
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送失败" + s);
        }

    }
}
