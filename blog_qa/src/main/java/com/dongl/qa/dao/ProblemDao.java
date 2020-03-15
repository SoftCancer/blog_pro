package com.dongl.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongl.qa.entity.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 问答数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * @Description: 获取最新回复
     * 根据最新回复时间获取最新回复
     * @Author: YaoGuangXun
     * @Date: 2020/3/15 14:11
     **/
    @Query(value = "SELECT * FROM tb_problem ,tb_pl WHERE id =  problemid AND labelid = ? ORDER BY replytime DESC",nativeQuery = true)
    Page<Problem> getNewProblem(String labelId , Pageable pageable);

    /**
     * @Description: 获取最热门回复
     *  根据回复数倒序获取最热回复
     * @Author: YaoGuangXun
     * @Date: 2020/3/15 14:11
     **/
    @Query(value = "SELECT * FROM tb_problem ,tb_pl WHERE id =  problemid AND labelid = ? ORDER BY reply DESC",nativeQuery = true)
    Page<Problem> getHotProblem(String labelId , Pageable pageable);

    /**
     * @Description: 获取待回答问题
     * 根据回复数是否为零获取待回复
     * @Author: YaoGuangXun
     * @Date: 2020/3/15 14:12
     **/
    @Query(value = "SELECT * FROM tb_problem ,tb_pl WHERE id =  problemid AND labelid = ? AND reply= 0 ORDER BY createtime DESC ",nativeQuery = true)
    Page<Problem> getWaitProblem(String labelId , Pageable pageable);


}
