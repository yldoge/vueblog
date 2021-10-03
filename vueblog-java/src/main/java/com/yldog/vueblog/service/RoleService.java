package com.yldog.vueblog.service;

import com.yldog.vueblog.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Role Table 服务类
 * </p>
 *
 * @author yldog
 * @since 2021-10-03
 */
public interface RoleService extends IService<Role> {

    /**
     * get a user's roles by that user's id
     * @param userId User ID
     * @return list of roles
     */
    List<Role> selectRolesByUserId(Long userId);

    /**
     * get a user's role keys by id
     * @param userId User ID
     * @return list of Role Key Strings
     */
    List<String> getRoleKeysByUserId(Long userId);

    Long getRoleIdByRoleKey(String key);

}
