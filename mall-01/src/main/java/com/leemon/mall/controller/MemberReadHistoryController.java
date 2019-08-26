package com.leemon.mall.controller;

import com.leemon.mall.common.api.CommenResult;
import com.leemon.mall.nosql.mongodb.document.MemberReadHistory;
import com.leemon.mall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-26 11:33
 * @desc 会员商品浏览记录管理Controller
 **/
@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;


    @ApiOperation("创建浏览记录")
    @PostMapping("/create")
    public CommenResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return CommenResult.success(count);
        } else {
            return CommenResult.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @PostMapping("/delete")
    public CommenResult delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return CommenResult.success(count);
        } else {
            return CommenResult.failed();
        }
    }

    @ApiOperation("展示浏览记录")
    @GetMapping("/list}")
    public CommenResult list(Long memberId) {
        List<MemberReadHistory> list = memberReadHistoryService.list(memberId);
        return CommenResult.success(list);
    }
}
