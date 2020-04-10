package com.dongl.user.interceptor;

import com.dongl.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/20 23:34
 * @Version: 1.0
 */
//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JwtUtil jwtUtil;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("经过拦截器。。。");
//
//        /**
//         * 拦截器只负责把请求头中包含的token令牌进行解析验证
//         * Authorization : 请求时自己定义的。
//         * auth : 中包含：dongl_  +  Token ，ag: dongl_qFTNLzNvo
//         * **/
//        String auth = request.getHeader("Authorization");
//
//        if (null != auth && !"".equals(auth)){
//            // 判断请求头中是否包含：dongl_
//            String tokenHead = "dongl_";
//            if (auth.startsWith(tokenHead)){
//                // 截取请求头获取 Token。
//                String token = auth.substring(tokenHead.length());
//
//                try{
//                    // 解析Token 字符串
//                    Claims claims = jwtUtil.parseJWT(token);
//                    Object roles = claims.get("roles");
//
//                    // 判断是否是admin权限
//                    if (null != roles && "admin".equals(roles)){
//                        request.setAttribute("claims_admin",token);
//                    }
//                    // 判断是否是普通用户
//                    if (null != roles && "user".equals(roles)){
//                        request.setAttribute("claims_user",token);
//                    }
//
//                }catch (Exception e){
//                    throw new RuntimeException("令牌错误！");
//                }
//            }
//        }
//        // 表示放行
//        return true;
//    }
//}
