/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/28/21
 * Time: 10:27 PM
 *
 * Project: vueblog-java
 * Package: com.yldog.vueblog.shiro.service
 * Class: LoginService
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.shiro.service;

import com.yldog.vueblog.common.enums.UserStatus;
import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.service.UserService;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class LoginService {

    @Resource
    private UserService userService;

    @Resource
    private PwdMatchService pwdMatchService;

    public User doLogin(String username, String password) {
        User loginUser = userService.getUserByUsername(username);

        // 检查用户是否存在
        if (loginUser == null) {
            throw new UnknownAccountException("用户名不存在");
        }

        // 检查用户账号状态
        if (UserStatus.BANNED.getCode() == loginUser.getStatus()) {
            throw new DisabledAccountException("账户已被封禁");
        }

        // 检查密码
        pwdMatchService.validate(loginUser, password);

        // 更新用户
        updateLastLogin(loginUser);

        return loginUser;
    }

    /**
     * 更新该用户的 last login time
     */
    private void updateLastLogin(User loginUser) {
        // mybatis-plus 更新指定字段
        User user = new User();
        user.setId(loginUser.getId());
        user.setLastLogin(LocalDateTime.now());
        userService.updateById(user);
    }
}
