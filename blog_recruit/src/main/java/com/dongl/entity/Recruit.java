package com.dongl.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/14 23:36
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "tb_recruit")
public class Recruit {

    @Id
    private String id;
    @Column(name = "jobname")
    private String jobName;
    private String salary;
    private String condition;
    private String education;
    private String type;
    private String address;
    private String eid;
    @Column(name = "createtime")
    private Date createTime;
    private String state;
    private String url;
    private String label;
    private String content1;
    private String content2;


}
