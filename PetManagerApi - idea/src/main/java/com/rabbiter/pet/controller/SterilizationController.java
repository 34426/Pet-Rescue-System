package com.rabbiter.pet.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.pet.service.IAnimalService;
import com.rabbiter.pet.entity.Sterilization;
import com.rabbiter.pet.service.ISterilizationService;
import com.rabbiter.pet.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbiter.pet.common.Result;
import org.springframework.web.multipart.MultipartFile;
import com.rabbiter.pet.entity.User;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/sterilization")
public class SterilizationController {

    @Resource
    private ISterilizationService sterilizationService;

    @Resource
    private IAnimalService animalService;

    private final String now = DateUtil.now();

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Sterilization sterilization) {
        sterilizationService.saveOrUpdate(sterilization);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sterilizationService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        sterilizationService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(sterilizationService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(sterilizationService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Sterilization> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("animal_name", name);
        }
        Page<Sterilization> page = sterilizationService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

}

