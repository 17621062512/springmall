package com.leemon.mall.componet;

import com.leemon.mall.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author limenglong
 * @create 2019-08-21 15:15
 * @desc 取消订单消息的发送者
 **/
@Component
public class CancelOrderSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //给延迟队列发送消息
    public void sendMessage(Long orderId, final long delayTimes) {
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange()
                , QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, message -> {
                    //给消息设置延迟毫秒值
                    message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                    return message;
                });

        LOGGER.info("send delay message orderId:{}", orderId);
    }

}
