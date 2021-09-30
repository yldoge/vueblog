/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/28/21
 * Time: 8:52 PM
 *
 * Project: vueblog-java
 * Package: com.yldog.vueblog.utils
 * Class: ShiroUtils
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * Shiro 的工具类
 */
public class ShiroUtils {

    /**
     * 生成随机的盐值
     */
    public static String generateRandomSalt() {
        SecureRandomNumberGenerator srnGenerator = new SecureRandomNumberGenerator();
        return srnGenerator.nextBytes(3).toHex();
    }

}
