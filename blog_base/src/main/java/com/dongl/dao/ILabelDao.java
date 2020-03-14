package com.dongl.dao;

import com.dongl.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository<Label,String> 包含jpa 中的简单方法，Label：实体 ，String：Label 对象id的类型。
 * JpaSpecificationExecutor： 包含复杂的条件查询，排序，分页查询。
 * @Description: 标签接口类
 * @author: YaoGuangXun
 * @date: 2020/3/14 15:12
 * @Version: 1.0
 */
public interface ILabelDao extends JpaRepository<Label,String> , JpaSpecificationExecutor<Label> {

}
