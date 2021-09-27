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
    User getUserByUsername(String username);

}
