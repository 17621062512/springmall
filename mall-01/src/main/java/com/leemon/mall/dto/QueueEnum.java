package com.leemon.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息队列枚举配置
 */
@Getter
@AllArgsConstructor
public enum QueueEnum {

    //消息通知队列
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),
    //消息通知ttl队列
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall.order.cancel.ttl", "mall.order.cancel.ttl");

    //交换名称
    private String exchange;
    //队列名称
    private String name;
    //路由键
    private String routeKey;

}
