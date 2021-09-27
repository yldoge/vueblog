/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/21/21
 * Time: 7:33 PM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.com.yldog.vueblog.utils
 * Class: JwtToken
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.com.yldog.vueblog.utils;

import com.yldog.vueblog.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class JwtToken {

    @Test
    public void testCreate() {
        System.out.println(JwtUtils.createToken("yldog"));
    }
}
