package com.rabbiter.pet.mapper;

import com.rabbiter.pet.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-04-04
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    @Select("select * from notice order by id desc limit 5 ")
    List<Notice> limit(int i);
}
