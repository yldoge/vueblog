package com.yldog.vueblog.service.impl;

import com.alibaba.druid.pool.WrapperAdapter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yldog.vueblog.entity.Blog;
import com.yldog.vueblog.mapper.BlogMapper;
import com.yldog.vueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    public IPage<Blog> selectBlogPage(Page<Blog> page, String search) {
        return blogMapper.selectPage(page,
                Wrappers.<Blog>lambdaQuery().like(Blog::getTitle, search)
                        .orderByDesc(Blog::getCreated));
    }

}
