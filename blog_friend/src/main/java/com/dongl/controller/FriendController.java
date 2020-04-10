package com.dongl.controller;

import com.dongl.entity.Result;
import com.dongl.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 喜欢朋友的文章功能
 * @author: YaoGuangXun
 * @date: 2020/3/22 0:36
 * @Version: 1.0
 */
@RequestMapping(value = "/friend")
@RestController
public class FriendController {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    /**
     * friendid : 所喜欢的用户id
     * type：是否喜欢好友，1：喜欢，0：不喜欢。
     * @Description:
     * @Author: YaoGuangXun
     * @Date: 2020/3/22 0:57
     **/
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        Claims claims_user = (Claims) request.getAttribute("claims_user");
        if (null == claims_user){
            return Result.error("权限不足");
        }

        String userid = claims_user.getId();
        if (null != type){
            if ("1".equals(type)){
                int flag = friendService.addFriend(userid,friendid);
                if (flag ==0){
                    return Result.error("不能重复添加！");
                }
                if (flag == 1){
                    return Result.success("好友添加成功！");
                }
            }else if ("2".equals(type)){
                int flag = friendService.addNoFriend(userid,friendid);
                if (flag ==0){
                    return Result.error("不能重复非好友！");
                }
                if (flag == 1){
                    return Result.success("好友添加成功！");
                }
                if (flag == 2){
                    return Result.error("添加异常");
                }
            }
        }
        return Result.error("参数异常");
    }

}
