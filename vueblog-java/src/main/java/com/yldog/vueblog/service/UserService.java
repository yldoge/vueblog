package com.yldog.vueblog.service;

import com.yldog.vueblog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yldog
 * @since 2021-09-20
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查找用户
     * @param username -- 用户名
     * @return 用户
     */
    User getUserByUsername(String username);

    int registerUser(User user);

}
