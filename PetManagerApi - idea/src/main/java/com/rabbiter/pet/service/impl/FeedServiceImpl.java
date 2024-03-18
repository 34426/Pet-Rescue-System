package com.rabbiter.pet.service.impl;

import com.rabbiter.pet.entity.Feed;
import com.rabbiter.pet.mapper.FeedMapper;
import com.rabbiter.pet.service.IFeedService;
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
public class FeedServiceImpl extends ServiceImpl<FeedMapper, Feed> implements IFeedService {

}
