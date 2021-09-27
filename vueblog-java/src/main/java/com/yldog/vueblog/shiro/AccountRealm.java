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

import com.yldog.vueblog.entity.User;
import com.yldog.vueblog.service.UserService;
import com.yldog.vueblog.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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

        return new SimpleAuthenticationInfo(user.getId(), tokenContent, getName());
    }
}
