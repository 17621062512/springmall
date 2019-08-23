package com.leemon.mall.componet;

import com.leemon.mall.dto.QueueEnum;
import com.leemon.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author limenglong
 * @create 2019-08-21 16:06
 * @desc 取消订单消息的处理者
 **/
@Component
@Configuration
@EnableRabbit
public class CancelOrderReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OmsPortalOrderService protalOrderService;

    @RabbitListener(queues = "mall.order.cancel")
    @RabbitHandler
    public void handle(Long orderId) {

        LOGGER.info("receive delay message orderId:{}", orderId);
        protalOrderService.cancelOrder(11L);

    }

}
