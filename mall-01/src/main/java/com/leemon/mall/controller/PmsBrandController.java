package com.leemon.mall.controller;

import com.leemon.mall.common.api.CommenPage;
import com.leemon.mall.common.api.CommenResult;
import com.leemon.mall.mbg.model.PmsBrand;
import com.leemon.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-06 15:16
 * @desc 品牌管理Controller
 **/

@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("获取所有品牌列表")
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommenResult<List<PmsBrand>> getBrandList() {
        return CommenResult.success(pmsBrandService.listAllBrand());
    }


    @ApiOperation("添加品牌")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('pms:brand:create')")
    public CommenResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommenResult commenResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            commenResult = CommenResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commenResult = CommenResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commenResult;
    }

    @ApiOperation("更新指定ID品牌信息")
    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommenResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto) {
        CommenResult commenResult;
        int count = pmsBrandService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commenResult = CommenResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commenResult = CommenResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commenResult;
    }


    @ApiOperation("删除指定id的品牌")
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public CommenResult deleteBrand(@PathVariable("id") Long id) {
        CommenResult commenResult;
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            commenResult = CommenResult.success(null);
            LOGGER.debug("deleteBrand success :id={}", id);
        } else {
            commenResult = CommenResult.failed("操作失败");
            LOGGER.debug("deleteBrand failed :id={}", id);
        }
        return commenResult;
    }

    @ApiOperation("分页查询品牌列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommenResult<CommenPage> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "3") @ApiParam("每页数量") Integer pageSize) {

        List<PmsBrand> brands = pmsBrandService.listBrand(pageNum, pageSize);
        return CommenResult.success(CommenPage.restPage(brands));

    }

    @ApiOperation("获取指定id的品牌信息")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommenResult brand(@PathVariable("id") Long id) {
        PmsBrand brand = pmsBrandService.getBrand(id);
        return CommenResult.success(brand);
    }
}
