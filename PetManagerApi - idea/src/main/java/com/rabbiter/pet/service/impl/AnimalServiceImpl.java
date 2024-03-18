package com.rabbiter.pet.service.impl;

import com.rabbiter.pet.entity.Animal;
import com.rabbiter.pet.mapper.AnimalMapper;
import com.rabbiter.pet.service.IAnimalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-04-02
 */
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements IAnimalService {

}
