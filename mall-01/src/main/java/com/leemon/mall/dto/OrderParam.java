package com.leemon.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author limenglong
 * @create 2019-08-21 16:18
 * @desc 生成订单时传入的参数
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderParam {
    //收货地址id
    private Long memberReceiveAddressId;
    //优惠券id
    private Long couponId;
    //使用的积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType;

}
