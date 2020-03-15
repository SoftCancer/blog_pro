package com.dongl.dao;

import com.dongl.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/1423:40
 * @Version: 1.0
 */
public interface IEnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise> {

    public List<Enterprise> findByIshot(String ishot);
}
