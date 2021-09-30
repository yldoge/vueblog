/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/22/21
 * Time: 10:44 PM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.controller
 * Class: LoginController
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yldog.vueblog.common.Result;
import com.yldog.vueblog.entity.LoginInfo;
import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.service.LoginInfoService;
import com.yldog.vueblog.service.UserService;
import com.yldog.vueblog.shiro.service.LoginService;
import com.yldog.vueblog.utils.JwtUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Resource
    private LoginInfoService loginInfoService;

    @Resource
    private LoginService loginService;

    @PostMapping()
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {

        try {
            User loginUser = loginService.doLogin(username, password);
            return Result.success("登录成功", JwtUtils.createToken(loginUser.getUsername()));
        } catch (Exception e) {
            return Result.error("用户名或密码错误!");
        }

    }

    @GetMapping("/info")
    @RequiresAuthentication
    public Result getLoginInfo(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success("请求成功",
                loginInfoService.page(new Page<>(pageNum, pageSize),
                        Wrappers.<LoginInfo>lambdaQuery()
                                .orderByDesc(LoginInfo::getLoginTime)));
    }

    @GetMapping("/unauthorized/{message}")
    public Result toUnauthorized( @PathVariable("message") String message) {
        return Result.error(message);
    }

}
