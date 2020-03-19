package com.dongl.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @Description: 解密jwt 测试
 * @author: YaoGuangXun
 * @date: 2020/3/20 1:16
 * @Version: 1.0
 */
public class ParesJwtTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        Claims claims = Jwts.parser()
                .setSigningKey("dongl")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4OTg5Iiwic3ViIjoiQWxiZXJ0IiwiaWF0IjoxNTg0NjM4OTQxLCJleHAiOjE1ODQ2MzkwMDEsInJvbGUiOiJhZG1pbiJ9.nqZQCU8tbHEOl1v92nasvYb5KDHURD_75kMa6Oc4bpY")
                .getBody();

        System.out.println("用户ID："+claims.getId());
        System.out.println("用户名："+claims.getSubject());
        System.out.println("登录时间："+ sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间："+ sdf.format(claims.getExpiration()));
        System.out.println("自定义参数："+ claims.get("role"));

    }
}
