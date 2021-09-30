/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/28/21
 * Time: 10:54 PM
 *
 * Project: vueblog-java
 * Package: com.yldog.vueblog.shiro.service
 * Class: PwdMatchService
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.shiro.service;

import com.yldog.vueblog.common.Factory.TaskFactory;
import com.yldog.vueblog.common.Manager.AsyncManager;
import com.yldog.vueblog.entity.User;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

@Component
public class PwdMatchService {

    public void validate(User user, String loginPassword) {
        if (!match(user, loginPassword)) {
            AsyncManager.getTheManager().execute(TaskFactory.collectLoginInfo(user.getUsername(), "密码错误"));
            throw new IncorrectCredentialsException("密码错误");
        }
    }

    private boolean match(User user, String loginPassword) {
        return user.getPassword().equals(encryptPwd(user.getUsername(), loginPassword, user.getSalt()));
    }

    private String encryptPwd(String username, String loginPassword, String salt) {
        return new Md5Hash(username + loginPassword + salt).toHex();
    }
}
