/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/30/21
 * Time: 4:13 PM
 *
 * Project: vueblog-java
 * Package: com.yldog.vueblog.common.Manager
 * Class: ShutDownManager
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.common.Manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class ShutDownManager {

    private static final Logger logger = LoggerFactory.getLogger("sys-user");

    @PreDestroy
    public void destory() {
        shutdownAsyncManager();
    }

    private void shutdownAsyncManager()
    {
        try
        {
            logger.info("====关闭后台任务任务线程池====");
            AsyncManager.getTheManager().shutdown();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
    }
}
