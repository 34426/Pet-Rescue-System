package com.rabbiter.pet.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.pet.entity.Animal;
import com.rabbiter.pet.service.IAnimalService;
import com.rabbiter.pet.entity.Applcation;
import com.rabbiter.pet.service.IApplcationService;
import com.rabbiter.pet.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
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
@RequestMapping("/applcation")
public class ApplcationController {

    @Resource
    private IApplcationService applcationService;

    @Resource
    private IAnimalService animalService;

    private final String now = DateUtil.now();

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Applcation applcation) {
        applcationService.saveOrUpdate(applcation);
        return Result.success();
    }

    @PostMapping("/state/{id}/{state}")
    public Result state(@PathVariable Integer id, @PathVariable String state) {
        Applcation applcation = applcationService.getById(id);
        applcation.setState(state);

        QueryWrapper<Applcation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("animal_id", applcation.getAnimalId());
        List<Applcation> list = applcationService.list(queryWrapper);
        for (Applcation app : list) {
            app.setState("审核不通过");
            applcationService.updateById(app);
        }

        applcationService.updateById(applcation);

        Animal animal = animalService.getById(applcation.getAnimalId());
        animal.setIsAdopt("是");
        animal.setAdopt("不可领养");
        animalService.updateById(animal);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        applcationService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        applcationService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(applcationService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(applcationService.getById(id));
    }

    @GetMapping("/my")
    public Result my() {
        List<Animal> animals = animalService.list();
        QueryWrapper<Applcation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        User currentUser = TokenUtils.getCurrentUser();
        if (currentUser == null) {
            return Result.success(new ArrayList<>());
        }
        queryWrapper.eq("user_id", currentUser.getId());
        List<Applcation> applcations = applcationService.list(queryWrapper);
        for (Applcation record : applcations) {
            animals.stream().filter(animal -> animal.getId().equals(record.getAnimalId())).findFirst().ifPresent(record::setAnimal);
        }
        return Result.success(applcations);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        List<Animal> animals = animalService.list();
        QueryWrapper<Applcation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        Page<Applcation> page = applcationService.page(new Page<>(pageNum, pageSize), queryWrapper);
        for (Applcation record : page.getRecords()) {
            animals.stream().filter(animal -> animal.getId().equals(record.getAnimalId())).findFirst().ifPresent(record::setAnimal);
        }
        return Result.success(page);
    }

}

