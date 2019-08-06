package com.leemon.mall.controller;

import com.leemon.mall.common.api.CommenPage;
import com.leemon.mall.common.api.CommenResult;
import com.leemon.mall.mbg.model.PmsBrand;
import com.leemon.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-06 15:16
 * @desc 品牌管理Controller
 **/

@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);


    @GetMapping("/listAll")
    public CommenResult<List<PmsBrand>> getBrandList() {
        return CommenResult.success(pmsBrandService.listAllBrand());
    }


    @PostMapping("/create")
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

    @PostMapping("/update/{id}")
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


    @GetMapping("/delete/{id}")
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

    @GetMapping("/list")
    public CommenResult<CommenPage> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {

        List<PmsBrand> brands = pmsBrandService.listBrand(pageNum, pageSize);
        return CommenResult.success(CommenPage.restPage(brands));

    }

    @GetMapping("/{id}")
    public CommenResult brand(@PathVariable("id") Long id) {
        PmsBrand brand = pmsBrandService.getBrand(id);
        return CommenResult.success(brand);
    }
}
