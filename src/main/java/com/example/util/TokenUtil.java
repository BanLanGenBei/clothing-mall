package com.example.util;




import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 陈冲
 * @Date: 2020/12/21 14:52
 */
public class TokenUtil {
    //Token的过期时间
    private static final long EXPIRE_TIME = 30 * 60 * 1000 * 24;//12小时
    //Token的私钥
    private static final String TOKEN_SECRET = "clothing.mall";


    /**
     * 生成签名，30分钟过期
     * @return
     */
    public static String sign(Integer userId) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("Type", "Jwt");//表示令牌的类型，JWT令牌统一写为JWT
            header.put("alg", "HS256");//签名使用的算法
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            JWTVerifier verifier = JWT.require(algorithm).build();
            //使用以前配置的任何选项，针对给定令牌执行验证。
            verifier.verify(token);//未验证通过会抛出异常
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 从token中获取info信息
     * @param **token**
     * @return
     */
    public static String getUserId(String token,String info){
        try {

            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(info).asString();
        } catch (
                JWTDecodeException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String sign = sign(121);
        System.out.println(sign);

        String userId = getUserId(sign, "userId");
        System.out.println(userId);
    }

}
