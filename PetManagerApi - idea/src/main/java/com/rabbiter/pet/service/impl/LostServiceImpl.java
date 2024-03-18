package com.rabbiter.pet.service.impl;

import com.rabbiter.pet.service.ILostService;
import com.rabbiter.pet.entity.Lost;
import com.rabbiter.pet.mapper.LostMapper;
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
public class LostServiceImpl extends ServiceImpl<LostMapper, Lost> implements ILostService {

}
