/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/21/21
 * Time: 3:14 PM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.shiro
 * Class: AccountRealm
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.shiro;

import com.yldog.vueblog.entity.Role;
import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.service.RoleService;
import com.yldog.vueblog.service.UserService;
import com.yldog.vueblog.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // Get the current user's ID from shiro
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getId();

        // add roles for that user in authorization
        info.addRoles(roleService.getRoleKeysByUserId(userId));

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String tokenContent = (String) jwtToken.getCredentials();
        String username = JwtUtils.getUsernameByToken(tokenContent);
        if (!JwtUtils.verify(tokenContent) | username == null) {
            throw new AuthenticationException("token认证失败：token错误或过期。请重新登录");
        }

        User user = userService.getUserByUsername(username);

        return new SimpleAuthenticationInfo(user, tokenContent, getName());
    }
}
