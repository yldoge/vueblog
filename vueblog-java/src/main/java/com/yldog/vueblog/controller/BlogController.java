package com.yldog.vueblog.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yldog.vueblog.common.Result;
import com.yldog.vueblog.entity.Blog;
import com.yldog.vueblog.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yldog
 * @since 2021-09-20
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    // 游客允许查看，所以不需要token
    // 根据 分页信息 和 对title的模糊查询，获得相应的博客列表，并从最新创建的博客开始
    @GetMapping("/blogs")
    public Result getBlogs(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5") Integer pageSize,
                           @RequestParam(defaultValue = "") String titleMatch) {
        return Result.success("请求成功",
                blogService.page(new Page<>(pageNum, pageSize),
                        Wrappers.<Blog>lambdaQuery()
                                .like(Blog::getTitle, titleMatch)
                                .orderByDesc(Blog::getCreated)));
    }

    // 博客详情页
    @GetMapping("/{id}")
    public Result getBlogDetail (@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        return blog == null ? Result.error(404, "该博客已被删除") : Result.success("请求成功", blog);
    }

//    // 创建、编辑博客
//    @PostMapping("/edit")
//    @RequiresAuthentication
//    public Result edit(@RequestBody Blog blog) {
//        if (blog.getId() == null) { // 说明是要创建一篇新的博客
//            blogService.save(Blog.create(blog.getUserId(), blog.getTitle(), blog.getDescription(),
//                    blog.getContent(), blog.getStatus()));
//            return Result.success("创建成功");
//        } else { // 说明要编辑一篇现有的博客
//
//            // 从subject中获得 userId
//            Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
//
//            if (userId.equals(blog.getUserId())) {
//                blogService.saveOrUpdate(blog);
//                return Result.success("编辑成功");
//            } else {
//                return Result.error("您没有编辑这篇博客的权限");
//            }
//        }
//    }

}
