package com.yldog.vueblog.controller;


import com.yldog.vueblog.common.Result;
import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/test")
    @RequiresAuthentication
    public Result test() {
        return Result.success("test");
    }

    @GetMapping("/{id}")
    @RequiresAuthentication
    public Result getUserById(@PathVariable("id") Long id) {
        return Result.success("请求成功", userService.getById(id));
    }

    @GetMapping()
    @RequiresAuthentication
    public Result getUserList(@RequestParam("pageSize") int pageSize,
                              @RequestParam("pageNum") int pageNum,
                              @RequestParam("username") String search) {



        return Result.success("请求成功");
    }

}
