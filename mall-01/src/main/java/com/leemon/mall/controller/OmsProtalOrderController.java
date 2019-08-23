package com.leemon.mall.controller;

import com.leemon.mall.dto.OrderParam;
import com.leemon.mall.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author limenglong
 * @create 2019-08-21 18:04
 * @desc 订单管理controller
 **/
@RestController
@Api(tags = "OmsProtalOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsProtalOrderController {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @PostMapping("/generateOrder")
    public Object generarteOrder(@RequestBody OrderParam orderParam) {
        return omsPortalOrderService.generateOrder(orderParam);
    }
}
