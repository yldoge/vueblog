package com.yldog.vueblog.mapper;

import com.yldog.vueblog.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Role Table Mapper 接口
 * </p>
 *
 * @author yldog
 * @since 2021-10-03
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * get a user's roles by that user's id
     * @param userId user id
     * @return list of roles
     */
    List<Role> selectRolesByUserId(Long userId);

}
