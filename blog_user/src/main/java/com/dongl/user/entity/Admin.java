package com.dongl.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 管理员实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "tb_admin")
public class Admin implements Serializable {

    @Id
    private String id;//ID

    @Column(name = "loginname")
    private String loginName;//登陆名称
    private String password;//密码
    private String state;//状态

}
