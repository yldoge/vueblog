package com.yldog.vueblog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yldog.vueblog.entity.Role;
import com.yldog.vueblog.mapper.RoleMapper;
import com.yldog.vueblog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Role Table 服务实现类
 * </p>
 *
 * @author yldog
 * @since 2021-10-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<String> getRoleKeysByUserId(Long userId) {
        return selectRolesByUserId(userId)
                .stream()
                .map(Role::getRoleKey)
                .collect(Collectors.toList());
    }

    @Override
    public Long getRoleIdByRoleKey(String key) {
        return roleMapper
                .selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getRoleKey, key))
                .getRoleId();
    }


}
