package com.dongl.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:  标签实体类
 * @author: YaoGuangXun
 * @date: 2020/3/14 14:47
 * @Version: 1.0
 */
@Entity
@Data
@Table(name = "tb_label")
public class Label implements Serializable {

    /** id **/
    @Id
    private String id;
    /** 标签名称 **/
    private String labelname;
    /** 状态 **/
    private String state;
    /** 使用次数 **/
    private String count;
    /** 关注数量 **/
    private String fans;
    /** 是否推荐 **/
    private String recommend;
}
