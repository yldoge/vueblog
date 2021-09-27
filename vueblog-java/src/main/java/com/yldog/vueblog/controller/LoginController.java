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
import com.yldog.vueblog.common.Result;
import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.service.UserService;
import com.yldog.vueblog.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        User loginUser = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (loginUser == null) { return Result.error("用户名不存在！"); }
        if (!loginUser.getPassword().equals(password)) {
            return Result.error("密码错误!");
        }
        return Result.success("登录成功", JwtUtils.createToken(loginUser.getUsername()));
    }

    @GetMapping("/unauthorized/{message}")
    public Result toUnauthorized( @PathVariable("message") String message) {
        return Result.error(message);
    }

}
