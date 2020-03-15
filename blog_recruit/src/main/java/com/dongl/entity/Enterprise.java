package com.dongl.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: 企业信息实体
 * @author: YaoGuangXun
 * @date: 2020/3/14 23:19
 * @Version: 1.0
 */

@Entity
@Table(name = "tb_enterprise")
@Data
public class Enterprise implements Serializable {


    /** id **/
    @Id
    private String id;
    /** 企业名称 **/

    private String name;
    /** 企业简介 **/
    private String summary;

    /** 企业地址 **/
    private String address;
    /** 标签列表 **/
    private String labels;
    /** 企业坐标 **/
    private String coordinate;
    /** 是否热门 **/
    private String ishot;
    /** 企业logo **/
    private String logo;
    /** 职位数 **/
    @Column(name = "jobcount")
    private Integer jobCount;
    /** URL **/
    private String url;

}
