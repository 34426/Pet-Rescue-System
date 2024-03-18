package com.rabbiter.pet.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.pet.entity.User;
import com.rabbiter.pet.service.IUserService;
import com.rabbiter.pet.utils.TokenUtils;
import com.rabbiter.pet.entity.Comment;
import com.rabbiter.pet.service.ICommentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbiter.pet.common.Result;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @Resource
    private IUserService userService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Comment comment) {
        comment.setUser(TokenUtils.getCurrentUser().getNickname());
        comment.setTime(DateUtil.now());
        commentService.saveOrUpdate(comment);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commentService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commentService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(commentService.list());
    }

    @GetMapping("/article/{type}/{articleId}")
    public Result findAll(@PathVariable Integer type, @PathVariable Integer articleId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("type", type);
        List<Comment> list = commentService.list(queryWrapper);
        List<Comment> res = new ArrayList<>();
        for (Comment comment : list) {
            User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getNickname, comment.getUser()));
            if (one != null) {
                comment.setAvatar(one.getAvatarUrl());  // 设置头像
            }
            if (comment.getPid() == null) {
                res.add(comment);
                List<Comment> children = list.stream().filter(c -> comment.getId().equals(c.getPid())).collect(Collectors.toList());
                comment.setChildren(children);
            }
        }
        return Result.success(res);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(commentService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(commentService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


}

