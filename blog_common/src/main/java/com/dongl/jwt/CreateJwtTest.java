package com.dongl.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 *  jwt 加密测试
 * @Description: JWT 包含:头部，载体，签名
 * @author: YaoGuangXun
 * @date: 2020/3/20 0:56
 * @Version: 1.0
 */
public class CreateJwtTest {

    public static void main(String[] args) {

        JwtBuilder jwtBuilder = Jwts.builder().setId("8989")
                .setSubject("Albert")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "dongl")
                .setExpiration(new Date(System.currentTimeMillis() + 60*1000))  // 设置过期时间
                .claim("role","admin"); // 自定义参数


        System.out.println("Jwt 加密： "+ jwtBuilder.compact());
    }
}
