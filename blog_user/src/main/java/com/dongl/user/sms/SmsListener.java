package com.dongl.user.sms;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 通过RabbitMQ 获取消息 实现短信发送
 * 监听验证码队列
 * @author: YaoGuangXun
 * @date: 2020/3/19 15:20
 * @Version: 1.0
 */
@Component
@RabbitListener(queues = "checkcode.queue")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code;


    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    @RabbitHandler
    public void executeSms(Map<String ,String> map){
        String mobile = map.get("mobile");
        String checkCode = map.get("checkCode");

        try {
            /**
             * code ：为阿里云 短信模板管理中的Code
             * @Description: 短信发送功能
             * @Author: YaoGuangXun
             * @Date: 2020/3/19 15:29
             * TemplateParam： {"name":"Tom","code":"123"}
             **/
            smsUtil.sendSms(mobile,template_code,sign_name,"{\"code\":\""+checkCode+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
