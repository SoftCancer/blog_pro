package com.dongl.spit.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 吐槽实体类
 * @author: YaoGuangXun
 * @date: 2020/3/16 17:40
 * @Version: 1.0
 */
@Data
public class Spit implements Serializable {

    @Id
    private String _id;
    private String content;
    /** 发布日期 **/
    private Date publishtime;

    private String userid;
    private String nickname;
    /** 浏览量 **/
    private Integer visits;
    /** 点赞数 **/
    private Integer thumbup;
    /** 分享量 **/
    private Integer share;
    /** 评论数 **/
    private Integer comment;
    /** 状态 **/
    private String state;
    private String parentid;

}
