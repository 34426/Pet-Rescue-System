package com.rabbiter.pet.service;

import com.rabbiter.pet.controller.dto.UserDTO;
import com.rabbiter.pet.controller.dto.UserPasswordDTO;
import com.rabbiter.pet.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2023-01-26
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);
}
