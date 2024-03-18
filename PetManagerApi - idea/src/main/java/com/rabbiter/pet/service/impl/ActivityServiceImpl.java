package com.rabbiter.pet.service.impl;

import com.rabbiter.pet.entity.Activity;
import com.rabbiter.pet.mapper.ActivityMapper;
import com.rabbiter.pet.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-04-04
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
