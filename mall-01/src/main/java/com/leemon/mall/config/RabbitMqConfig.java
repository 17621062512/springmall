package com.leemon.mall.config;

import com.leemon.mall.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author limenglong
 * @create 2019-08-21 11:11
 * @desc rabbitmq消息队列配置
 **/
@Configuration
public class RabbitMqConfig {

    /**
     * 订单消息实际消费队列所绑定的交换机
     *
     * @return
     */
    @Bean
    DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true).build();
    }

    /**
     * 订单延迟队列所绑定的交换机
     *
     * @return
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true).build();
    }

    /**
     * 订单消息实际消费队列
     *
     * @return
     */
    @Bean
    Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName(), true);
    }

    /**
     * 订单延迟队列（死信队列）
     *
     * @return
     */
    @Bean
    Queue orderTtlQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange());
        params.put("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());

        return new Queue(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName(), true, false, false, params);
    }

    /**
     * 将订单队列绑定到交换机
     */
    @Bean
    Binding orederBinding(DirectExchange orderDirect, Queue orderQueue) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }

    /**
     * 将延时队列绑定到交换机
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect, Queue orderTtlQueue) {
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }

}
