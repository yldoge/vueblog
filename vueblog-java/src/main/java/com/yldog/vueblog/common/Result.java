/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/21/21
 * Time: 11:48 AM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.common
 * Class: Result
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 400;

    public static Result success(String msg, Object data) {
        return new Result(SUCCESS_CODE, msg, data);
    }

    public static Result success(String msg) {
        return success(msg, null);
    }

    public static Result error(String msg, Object data) {
        return new Result(ERROR_CODE, msg, data);
    }

    public static Result error(String msg) {
        return error(msg, null);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }





}
