package com.dongl.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/22 0:02
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "tb_friend")
@IdClass(FriendEntity.class)
public class FriendEntity implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

    private String islike;
}
