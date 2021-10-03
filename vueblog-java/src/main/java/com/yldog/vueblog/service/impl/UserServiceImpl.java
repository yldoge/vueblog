package com.yldog.vueblog.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yldog.vueblog.common.contants.UserConstants;
import com.yldog.vueblog.entity.RoleUser;
import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.mapper.RoleUserMapper;
import com.yldog.vueblog.mapper.UserMapper;
import com.yldog.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yldog.vueblog.utils.ShiroUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.*;

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

    @Override
    public int registerUser(User user) {

        // check uniqueness of username
        Integer count = userMapper.selectCount(Wrappers
                .<User>lambdaQuery()
                .eq(User::getUsername, user.getUsername()));
        if (count > 0) { return UserConstants.USERNAME_NOT_UNIQUE; }

        // check uniqueness of email
        count = userMapper
                .selectCount(Wrappers
                .<User>lambdaQuery()
                .eq(User::getEmail, user.getEmail()));
        if (count > 0) {return UserConstants.EMAIL_NOT_UNIQUE; }

        user.setSalt(ShiroUtils.generateRandomSalt());
        user.setPassword(new Md5Hash(user.getUsername() +
                user.getPassword() +
                user.getSalt())
                .toHex());

        userMapper.insert(user);

        /**
         * set role for the user
         */
        RoleUser ur = new RoleUser();
        // all user added through registration will have a [common] role
        ur.setRoleId(SpringUtil.getBean(RoleServiceImpl.class).getRoleIdByRoleKey("common"));
        ur.setUserId(user.getId());
        SpringUtil.getBean(RoleUserMapper.class).insert(ur);

        return UserConstants.REGISTER_UNIQUE;
    }
}
