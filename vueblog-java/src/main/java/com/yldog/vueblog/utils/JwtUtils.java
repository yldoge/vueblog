/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 9/21/21
 * Time: 7:08 PM
 *
 * Project: vueblog
 * Package: com.yldog.vueblog.utils
 * Class: JwtUtils
 *
 * Description:
 *
 * ****************************************
 */
package
        com.yldog.vueblog.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    // jwt secret
    private static final String SECRET = "yldogxkiya";
    // token expire time
    private static final long EXPIRE = 5*60*1000L;
    // jwt header
    private static final Map<String, Object> HEADER = new HashMap<>();
    // jwt sign algorithm
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    static {
        HEADER.put("alg", ALGORITHM.getName());
        HEADER.put("typ", "JWT");
    }

    // 根据用户名为用户生成 JWt token
    public static String createToken(String username) {
        return JWT.create()
                .withHeader(HEADER)
                .withClaim("username", username)    // 私有声明 -- 用户名
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(ALGORITHM);
    }

    // 直接获取token中的信息
    public static String getUsernameByToken(String token) {
        try {
            return JWT.decode(token).getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token的有效性
     * 1. header/payload 是否被篡改过
     * 2. 是否过期
     */
    public static boolean verify(String token) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String testToken = JwtUtils.createToken("yaoluxunzhe");
        System.out.println(testToken);
        System.out.println(JwtUtils.getUsernameByToken(testToken));
        System.out.println(JwtUtils.verify(testToken+"999"));
    }


}
