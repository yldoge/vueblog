package com.yldog.vueblog.service.impl;

import com.yldog.vueblog.entity.LoginInfo;
import com.yldog.vueblog.mapper.LoginInfoMapper;
import com.yldog.vueblog.service.LoginInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * user login records 服务实现类
 * </p>
 *
 * @author yldog
 * @since 2021-09-30
 */
@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService {

}
