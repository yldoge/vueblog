/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/20/21
 * Time: 10:47 AM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.config
 * Class: AutoFillHandler
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yldog.vueblog.utils.ShiroUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AutoFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());

        // 自动填充盐值
        this.strictInsertFill(metaObject, "salt", String.class, ShiroUtils.generateRandomSalt());

        this.strictInsertFill(metaObject, "loginTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
