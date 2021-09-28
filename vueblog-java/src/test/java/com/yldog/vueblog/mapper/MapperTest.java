/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/24/21
 * Time: 7:48 PM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.mapper
 * Class: MapperTest
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yldog.vueblog.entity.Blog;
import com.yldog.vueblog.entity.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BlogMapper blogMapper;

    @Test
    public void addUserTest() {
        User user = new User();
        user.setUsername("yldog");
        user.setEmail("ly007@bucknell.edu");
        user.setStatus(0);
        user.setDelFlag(0);
        userMapper.insert(user);

        User userJustCreated = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, "yldog"));
        userJustCreated.setPassword(new Md5Hash(userJustCreated.getUsername() + "003773" + userJustCreated.getSalt()).toHex());
        userMapper.updateById(userJustCreated);
    }

    @Test
    public void addBlogTest() {
        for (int i = 2; i < 22; i++) {
            blogMapper.insert(Blog.create(1L,
                    "Blog " + i,
                    "Test Blog",
                    "This is a test blog",
                    1));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
