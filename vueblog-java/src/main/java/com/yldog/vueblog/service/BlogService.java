package com.yldog.vueblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yldog.vueblog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yldog
 * @since 2021-09-20
 */
public interface BlogService extends IService<Blog> {

    IPage<Blog> selectBlogPage(Page<Blog> page, String search);

}
