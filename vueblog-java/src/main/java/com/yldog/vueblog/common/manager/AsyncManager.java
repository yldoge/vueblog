/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/30/21
 * Time: 2:27 PM
 *
 * Project: vueblog-java
 * Package: com.yldog.vueblog.common.Manager
 * Class: AsyncManager
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.common.manager;

import cn.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * async tasks manager
 */
public class AsyncManager {

    private static final Logger logger = LoggerFactory.getLogger(AsyncManager.class);

    // operation delay time
    private final int OPERATION_DELAY_TIME = 10;

    // scheduled thread pool
    @Resource
    private ScheduledExecutorService executor = SpringUtil.getBean("scheduledExecutorService");

    /**
     * make sure the AsyncManager is singleton
     */
    private AsyncManager() {}

    private static AsyncManager theManager = null;

    private static Object LOCK = new Object();

    // DLC
    public static AsyncManager getTheManager() {
        if (theManager == null) {
            synchronized (LOCK) {
                if (theManager == null) {
                    theManager = new AsyncManager();
                }
            }
        }
        return theManager;
    }

    /**
     * execute the task
     * @param task
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATION_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        if (executor != null && !executor.isShutdown())
        {
            executor.shutdown();
            try
            {
                if (!executor.awaitTermination(120, TimeUnit.SECONDS))
                {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(120, TimeUnit.SECONDS))
                    {
                        logger.info("Pool did not terminate");
                    }
                }
            }
            catch (InterruptedException ie)
            {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

}
