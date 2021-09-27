package com.yldog.vueblog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.mapper.UserMapper;
import com.yldog.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yldog
 * @since 2021-09-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }
}
