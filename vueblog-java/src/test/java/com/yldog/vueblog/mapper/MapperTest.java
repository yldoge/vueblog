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

import com.yldog.vueblog.entity.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MapperTest {

    @Resource
    private BlogMapper blogMapper;

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
