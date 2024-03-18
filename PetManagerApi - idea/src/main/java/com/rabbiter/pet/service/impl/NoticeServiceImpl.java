package com.rabbiter.pet.service.impl;

import com.rabbiter.pet.entity.Notice;
import com.rabbiter.pet.mapper.NoticeMapper;
import com.rabbiter.pet.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-04-04
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> limit(int i) {
        return noticeMapper.limit(5);
    }
}
