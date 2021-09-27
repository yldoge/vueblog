/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/22/21
 * Time: 10:37 PM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.common.exception
 * Class: GlobalExceptionHandler
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.common.exception;

import com.yldog.vueblog.common.Result;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        return Result.error(401, e.getMessage());
    }
}
