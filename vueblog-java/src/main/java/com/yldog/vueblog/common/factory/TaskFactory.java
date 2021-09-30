/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/30/21
 * Time: 2:41 PM
 *
 * Project: vueblog-java
 * Package: com.yldog.vueblog.common.Factory
 * Class: TaskFactory
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.common.factory;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;
import com.yldog.vueblog.entity.LoginInfo;
import com.yldog.vueblog.service.LoginInfoService;
import com.yldog.vueblog.service.impl.LoginInfoServiceImpl;
import com.yldog.vueblog.utils.ServletUtils;
import org.apache.shiro.SecurityUtils;

import java.util.TimerTask;

public class TaskFactory {
    public static TimerTask collectLoginInfo(final String username, final String message) {
        UserAgent userAgent = UserAgentParser.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = SecurityUtils.getSubject().getSession().getHost();
        return new TimerTask() {
            @Override
            public void run() {
                // encapsulate login info
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setLoginName(username);
                loginInfo.setIpAddress(ip);
                loginInfo.setBrowser(userAgent.getBrowser().getName());
                loginInfo.setOs(userAgent.getOs().getName());
                loginInfo.setMsg(message);

                // insert into database
                LoginInfoService loginInfoService = SpringUtil.getBean(LoginInfoServiceImpl.class);
                loginInfoService.save(loginInfo);
            }
        };
    }
}
