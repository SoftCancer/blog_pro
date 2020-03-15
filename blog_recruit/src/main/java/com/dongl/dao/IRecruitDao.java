package com.dongl.dao;

import com.dongl.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description: 职位Dao接口
 * @author: YaoGuangXun
 * @date: 2020/3/150:42
 * @Version: 1.0
 */
public interface IRecruitDao extends JpaRepository<Recruit,String> ,JpaSpecificationExecutor {


    /**
     * @Description: 根据推荐职位的状态并且按时间倒序查询前6条推荐职位数据
     *  findTop6  ： 查询前6条数据
     *  state ： 2
     * @Author: YaoGuangXun
     * @Date: 2020/3/15 0:53
     **/
    public List<Recruit> findTop6ByStateOrderByCreateTimeDesc(String state);

    /**
     * @Description: 根据推荐职位的状态并且按时间倒序查询前6条最新职位
     *  findTop6  ： 查询前6条数据
     * @Author: YaoGuangXun
     * @Date: 2020/3/15 0:53
     **/
    public List<Recruit> findTop6ByStateNotOrderByCreateTimeDesc(String state);
}
