package com.leemon.mall.controller;

import com.leemon.mall.common.api.CommenPage;
import com.leemon.mall.common.api.CommenResult;
import com.leemon.mall.nosql.elasticsearch.document.EsProduct;
import com.leemon.mall.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-23 16:42
 * @desc 搜索商品管理controller
 **/
@RestController
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;


    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping("/importAll")
    public CommenResult<Integer> importAll() {
        int count = esProductService.importAll();

        return CommenResult.success(count);
    }


    @ApiOperation("根据id删除商品")
    @GetMapping("delete/{id}")
    public CommenResult delete(@PathVariable("id") Long id) {
        esProductService.delete(id);
        return CommenResult.success(null);
    }

    @ApiOperation("批量删除商品")
    @PostMapping("/delete/batch")
    public CommenResult delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommenResult.success(null);
    }

    @ApiOperation("根据id添加商品")
    @PostMapping("create/{id}")
    public CommenResult create(@PathVariable("id") Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return CommenResult.success(esProduct);
        }
        return CommenResult.failed();
    }

    @ApiOperation("简单搜索")
    @PostMapping("/search/simple")
    public CommenResult search(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize) {

        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommenResult.success(CommenPage.restPage(esProductPage));
    }
}
