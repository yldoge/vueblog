package com.yldog.vueblog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yldog.vueblog.entity.Blog;
import com.yldog.vueblog.service.BlogService;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class VueblogApplicationTests {

    @Resource
    private BlogService blogService;

    @Test
    void testBlogServicePage() {
        IPage<Blog> blog = blogService.selectBlogPage(new Page<>(1, 5), "Blog");
//        Page<Blog> blog1 = blogService.page(new Page<>(1, 5),
//                Wrappers.<Blog>lambdaQuery().like(Blog::getTitle, "Blog"));
        blog.getRecords().forEach(System.out::println);
    }

}
